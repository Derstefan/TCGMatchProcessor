package com.inftga.gamematch.core.card;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.AbilityCollector;
import com.inftga.gamematch.core.card.attribute.AttributCollector;
import com.inftga.gamematch.core.card.attribute.Attribute;
import com.inftga.gamematch.core.card.attribute.EAttribute;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.card.stats.Stats;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.state.MatchState;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.UUID;

@Slf4j
public class CardInstance {

    private final UUID uuid = UUID.randomUUID();
    private Card card;

    private Stats stats;

    private Pos pos;
    private AbilityCollector possibleAbili = new AbilityCollector();

    private AttributCollector attr;

    public CardInstance(Card card) {
        this.card = card;
        this.stats = card.getStats().clone();
        this.attr = card.getAttr().clone();
    }

    public boolean is(EAttribute a){
        return attr.contains(a);
    }

    //getter

    public Stats getStats() {
        return stats;
    }

    public AbilityCollector getPossibleAbili() {
        return possibleAbili;
    }

    public AttributCollector getAttr() {
        return attr;
    }

    public HashSet<Attribute> getAllAttributes() {
        return attr.getAllAttributes();
    }

    public Ability getPossibleAbility(int index){
        return possibleAbili.getAbility(index);
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public Card getCard(){
        return card;
    }

    public void computePossibleAbilities(MatchState state){
        possibleAbili.removeAll();
        for(Ability a:card.getAbili().getAll()){
            if(a.canPerform(state,this)){
                possibleAbili.add(a);
            }
        }
    }

    public boolean isAlife(){
        return stats.getLife()>0;
    }

    public void pureDemage(int pureDemage) {
        stats.setLife(stats.getLife()-pureDemage);
        if(stats.getLife()<0) stats.setLife(0);
    }

    public void heal(int heal){
        stats.setLife(stats.getLife()+heal);
        if(stats.getLife()> stats.getMaxLife()){
            stats.setLife(stats.getMaxLife());
        }
    }


    public EPlayer getOwner(){
        return pos.getP();
    }

    public void endRound(){
        possibleAbili.removeAll();
    }



    public String toString(){
        return card.getName();
    }
}
