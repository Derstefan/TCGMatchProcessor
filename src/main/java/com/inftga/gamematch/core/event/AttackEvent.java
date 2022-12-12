package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.card.CardInstance;

public class AttackEvent extends Event{
    private CardInstance attacker;
    private CardInstance defender;
    private int pureDemage;

    public AttackEvent(CardInstance attacker, CardInstance defender, int pureDemage) {
        this.attacker = attacker;
        this.defender = defender;
        this.pureDemage = pureDemage;
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

    @Override
    public String toString() {
        return attacker.toString() + " attacks " + defender.toString();
    }
}
