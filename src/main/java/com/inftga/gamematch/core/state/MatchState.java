package com.inftga.gamematch.core.state;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.AbilityCollector;
import com.inftga.gamematch.core.card.ability.movement.MoveAbility;
import com.inftga.gamematch.core.card.attribute.Attribute;
import com.inftga.gamematch.core.card.attribute.endRoundAttributes.EndRoundAttribute;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.deck.Deck;
import com.inftga.gamematch.core.event.*;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.Field;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.graveyard.GraveYard;
import com.inftga.gamematch.core.hand.Hand;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.player.Player;
import com.inftga.gamematch.match.EMatchStage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Slf4j
public class MatchState {


    private Player player1;
    private Player player2;
    private Deck deckP1;
    private Deck deckP2;

    private GraveYard graveyard1 = new GraveYard();

    private GraveYard graveyard2 = new GraveYard();
    private Hand handP1 = new Hand();
    private Hand handP2 = new Hand();
    private Field field = new Field();
    private EPlayer turn;

    private int turns = 0;

    private EventHistroy history = new EventHistroy();

    private EMatchStage stage;

/*    public MatchState(Deck deckP1, Deck deckP2){
        this.deckP1 = deckP1;
        this.deckP2 = deckP2;
    }*/

    public MatchState(Player player1, Player player2, EPlayer firstTurn){
        this.player1 = player1;
        this.player2= player2;
        this.deckP1 = player1.getDeckClone();
        this.deckP2 = player2.getDeckClone();
        this.turn = firstTurn;
        stage = EMatchStage.started;
    }

    public MatchState(){
        stage = EMatchStage.created;
    }

    public void joinPlayer(Player p){
        if(player1==null){
            player1=p;
            stage = EMatchStage.firstPlayerJoined;
            return;
        }
        if(player2==null){
            player2=p;
            stage = EMatchStage.secondPlayerJoined;
            stage = EMatchStage.started;
            return;
        }
        log.warn("Match has already 2 player. No else player can join.");
    }

    public boolean matchEnded(){
        return stage.equals(EMatchStage.firtPlayerWon) ||stage.equals(EMatchStage.secondPlayerWon) ||stage.equals(EMatchStage.matchIsADraw);
    }

    public void checkIfGameEnd(){
        if(player1.isDeath()){
            stage = EMatchStage.firtPlayerWon;
            if(player2.isDeath()){
                stage = EMatchStage.matchIsADraw;
            }
        } else if(player2.isDeath()){
            stage =EMatchStage.secondPlayerWon;
        }
    }


    public Optional<Card> takeNewCard(EPlayer p){
        Deck d = getDeck(p);
        Hand h = getHand(p);

        Optional<Card> c = d.getCard();
        if(c.isPresent()){
            h.addCard(c.get());
            d.removeCard();
        }
        return c;
    }

    public void putCardOnGraveyard(CardInstance cardInstance){
        field.removeCard(cardInstance.getPos());
        if(EPlayer.P1.equals(cardInstance.getOwner())){
            graveyard1.addCard(cardInstance.getCard());
        }
        if(EPlayer.P2.equals(cardInstance.getOwner())){
            graveyard2.addCard(cardInstance.getCard());
        }
        history.addEvent(new DiedEvent(cardInstance));
    }

    public void moveCard(CardInstance card, Pos toPos){
        if(field.isFree(toPos)){
            field.removeCard(card.getPos());
            field.putCardAt(card,toPos);
        }
    }

    public void computePossibleAbilities(){
        ArrayList<CardInstance> cardInstances = getAllCardInstances();
        for(CardInstance c:cardInstances){
            c.computePossibleAbilities(this);
        }
    }

    public void computePossibleAbilitiesOf(EPlayer p){
        ArrayList<CardInstance> cardInstances = getAllCardInstancesOf(p);
        for(CardInstance c:cardInstances){
            c.computePossibleAbilities(this);
        }
    }

    public void computeEndRoundAttributes(){
        ArrayList<CardInstance> cardInstances = getAllCardInstances();
        for(CardInstance c:cardInstances){
            for(Attribute a:c.getAllAttributes()){
                if(a instanceof EndRoundAttribute){
                    ((EndRoundAttribute)a).nextRound(this,c);
                }
            }
        }
    }

    public void computeEndRoundAttributesOf(EPlayer p){
        ArrayList<CardInstance> cardInstances = getAllCardInstancesOf(p);
        for(CardInstance c:cardInstances){
            for(Attribute a:c.getAllAttributes()){
                if(a instanceof EndRoundAttribute){
                    ((EndRoundAttribute)a).nextRound(this,c);
                }
            }
        }
    }



    public void placeCardFromHandToField(Card card, Pos pos){
        EPlayer p = pos.getP();
        Player player = getPlayer(p);
        Hand h = getHand(p);
        if(h.includes(card) && field.isFree(pos) && player.getGold()>=card.getStats().getCost()){
                player.pay(card.getStats().getCost());
                CardInstance cardInstance = new CardInstance(card);
                putCardOnField(cardInstance, pos);
                removeCardFromHand(card,p);
                history.addEvent(new PlaceCardEvent(card,pos));
                computePossibleAbilitiesOf(p);
        }
    }

    public void doAbility(CardInstance cardInstance, Ability ability) {
        Player player = getPlayer(cardInstance.getPos().getP());
        if(ability instanceof MoveAbility && !player.canMove()){
            log.warn("player cant move enymore");
            return;
        }
        if(ability.canPerform(this,cardInstance)){
            history.addEvent(new AbilityEvent(cardInstance,ability));
            ability.perform(this,cardInstance);


            if(ability instanceof MoveAbility){
                player.setCanMove(false);
            }
            endTurn();
            return;
        }
        log.warn("couldnt perform ability");
    }

    public void endTurn(){
        getPlayer(turn).setCanMove(true);
        computeEndRoundAttributesOf(turn);


        //Next Player
        turns++;
        turn = turn.getEnemy();
        computePossibleAbilitiesOf(turn);
        takeNewCard(turn);
    }

    public void addEvent(Event e){
        history.addEvent(e);
    }

    //private-------

    private void removeCardFromHand(Card card, EPlayer p){
        Hand h = getHand(p);
        h.removeCard(card);
    }

    private void putCardOnField(CardInstance card, Pos pos){
        if(field.isFree(pos)){
            field.putCardAt(card,pos);
        }
    }




    //GETTER SETTER -------------------------------------------------------------------

    public Player getPlayer(EPlayer p){
        if(player1.getPlayerType().equals(p)){
            return player1;
        }
        return player2;
    }
    public Optional<CardInstance> getCardAt(Pos pos){
        return field.getCardAt(pos);
    }

    public Hand getHand(EPlayer p){
        if(EPlayer.P1.equals(p)){
            return handP1;
        }
        return handP2;
    }

    public Deck getDeck(EPlayer p){
        if(EPlayer.P1.equals(p)){
            return deckP1;
        }
        return deckP2;
    }

    public boolean playerCanMove(EPlayer p){
        Player player = getPlayer(p);
        return player.canMove();
    }


    public Field getField() {
        return field;
    }

    public ArrayList<CardInstance> getAllCardInstances(){
        ArrayList<CardInstance> cardInstances = new ArrayList<>();
        cardInstances.addAll(getAllCardInstancesOf(EPlayer.P1));
        cardInstances.addAll(getAllCardInstancesOf(EPlayer.P2));
        return cardInstances;
    }

    public ArrayList<CardInstance> getAllCardInstancesOf(EPlayer p){
        ArrayList<CardInstance> cardInstances = new ArrayList<>();
        for(int i=0;i< Config.LINE_SIZE;i++){
            Optional<CardInstance> c = field.getCardAt(ELineType.FRONT,p,i);
            if(c.isPresent()){
                cardInstances.add(c.get());
            }
            c = field.getCardAt(ELineType.BACK,p,i);
            if(c.isPresent()){
                cardInstances.add(c.get());
            }
        }
        return cardInstances;
    }


    public boolean cardIsOnField(CardInstance c){
        Optional<CardInstance> c2 = field.getCardAt(c.getPos());
        if(c2.isPresent()){
            return c.equals(c2.get()) && c.getPos() ==c2.get().getPos() ;
        }
        return false;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player1;
    }

    public EPlayer getTurn(){
        return turn;
    }
    public int getRound(){
        return (int)turns/2;
    }

    public String toString(){
        String str = player1.toString()+" vs. " +player2.toString()+"\n";;
        str+="Rounds:" + getRound() + ", turn:" + getPlayer(turn).getName() + "\n";
        str+=field.toString();

        return str;
    }
}
