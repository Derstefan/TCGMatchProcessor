package com.inftga.gamematch.core.player;


import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.deck.Deck;

/**
 * Player class for a match.
 * That's why he has only one Deck.(It's not the stat but the complete collection of his cards.)
 *
 */
public class Player {

    /**
     * Type of Player for a Match. (P1 or P2)
     */
    private EPlayer playerType;

    /**
     * all of his cards, not the currecnt state of a match
     */
    private Deck originalDeck;


    private int gold = Config.START_GOLD;

    private int life = Config.MAX_LIFE;

    private String name;

    /**
     * Can us MoveAbilitys ?
     */
    private boolean canMove = true;


    public Player(EPlayer playerType, Deck originalDeck, String name) {
        this.playerType = playerType;
        this.originalDeck = originalDeck;
        this.name = name;
    }

    public EPlayer getPlayerType() {
        return playerType;
    }

    public Deck getDeckClone() {
        return originalDeck.clone();
    }

    public String getName() {
        return name;
    }

    public boolean canMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove= canMove;
    }

    public int getLife() {
        return life;
    }

    public void demage(int demage) {
        life = life-demage;
        if(life<0) life =0;
    }

    public void heal(int heal){
        life = life + heal;
        if(life>Config.MAX_LIFE){
            life = Config.MAX_LIFE;
        }
    }

    public boolean isDeath(){
        return getLife()==0;
    }

    public int getGold() {
        return gold;
    }

    public boolean pay(int amount) {
        if(gold-amount<0)return false;
        gold = gold-amount;
        return true;
    }

    public void earn(int amount){
        gold = gold + amount;
    }

    public boolean canPay(Card c){
        return c.getStats().getCost()<=gold;
    }


    public String toString(){
        return playerType + ": " + name + "," + gold+"G,"+life+"/"+Config.MAX_LIFE+"L.";
    }
}
