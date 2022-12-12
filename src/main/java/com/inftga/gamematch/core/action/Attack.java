package com.inftga.gamematch.core.action;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.event.AttackEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Attack {

    private CardInstance attacker;
    private CardInstance defender;

    private int pureDemage = -1;

    public Attack(CardInstance attacker, CardInstance defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void apply(){
        if(attacker.getStats().getDemage()==0 && attacker.getStats().getMagicDemage()==0){
            log.warn("card can not attack without demage");
            return;
        }
        if(attacker.getStats().getDemage()>=attacker.getStats().getMagicDemage()){
            //attacker is physical demage dealer
            pureDemage = Math.max(0,attacker.getStats().getDemage()-defender.getStats().getArmor());
            defender.pureDemage(pureDemage);
        } else if(attacker.getStats().getDemage()<attacker.getStats().getMagicDemage()){
            //attacker is magical demage dealer
            pureDemage = Math.max(0,attacker.getStats().getDemage()-defender.getStats().getArmor());
            defender.pureDemage(pureDemage);
        }
        log.info(attacker.getCard().getName() + " attacks " + defender.getCard().getName() + " with total amount of " + pureDemage + " demage");
    }

    public CardInstance getAttacker() {
        return attacker;
    }

    public CardInstance getDefender() {
        return defender;
    }

    public int getPureDemage() {
        return pureDemage;
    }
}
