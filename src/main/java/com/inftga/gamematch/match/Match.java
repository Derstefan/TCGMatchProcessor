package com.inftga.gamematch.match;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.movement.MoveAbility;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.event.EventHistroy;
import com.inftga.gamematch.core.event.PlaceCardEvent;
import com.inftga.gamematch.core.event.TakeCardEvent;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.Field;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.hand.Hand;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.player.Player;
import com.inftga.gamematch.core.state.MatchState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class Match {

    private final UUID matchId = UUID.randomUUID();

    private MatchState matchState;

    public Match(){
        matchState = new MatchState();
    }

    public Match(Player player1, Player player2){
        matchState = new MatchState(player1,player2,EPlayer.randomPlayer());
        init();
    }

    public void join(Player p){
        matchState.joinPlayer(p);
    }



    // ----- player Actions

    public void placeCard(Card c, Pos pos){
        if(matchEnded())return;

        matchState.placeCardFromHandToField(c,pos);

    }

    public void doAbility(CardInstance cardInstance, Ability ability) {
        if(matchEnded())return;
        matchState.doAbility(cardInstance, ability);
    }

    public void endTurn(){
        if(matchEnded())return;
        matchState.endTurn();
        matchState.checkIfGameEnd();
    }



    public boolean matchEnded(){
        return matchState.matchEnded();
    }

    public void endMatch(){
        matchState.checkIfGameEnd();
    }

    //private



    private void takeNewCard(EPlayer p){
        if(matchEnded())return;
        Optional<Card> c = matchState.takeNewCard(p);
        matchState.addEvent(new TakeCardEvent(p,c.get()));
    }

    private void init(){
        if(matchEnded())return;
        // take the first Cards
        for(int i = 0; i< Config.HAND_START_SIZE; i++){
            takeNewCard(EPlayer.P1);
            takeNewCard(EPlayer.P2);
        }
        matchState.computePossibleAbilities();
    }
    // getter
    public EPlayer getTurn(){
        return matchState.getTurn();
    }
    public Hand getHand(EPlayer p){
        return matchState.getHand(p);
    }

    public Field getField(){
        return matchState.getField();
    }

    public UUID getMatchId() {
        return matchId;
    }

    public String toString(){
        return matchState.toString();
    }


    public ArrayList<CardInstance> getAllCardInstances(){
        return matchState.getAllCardInstances();
    }

    public ArrayList<CardInstance> getAllCardInstancesOf(EPlayer p){
        return matchState.getAllCardInstancesOf(p);
    }


    public Player getPlayer(EPlayer p) {
        return matchState.getPlayer(p);
    }
}




