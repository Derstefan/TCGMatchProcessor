package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;

public class AbilityEvent extends Event{

    private CardInstance cardInstance;

    private Ability ability;

    public AbilityEvent(CardInstance cardInstance, Ability ability) {
        this.cardInstance = cardInstance;
        this.ability = ability;
    }

    public CardInstance getCardInstance() {
        return cardInstance;
    }

    public Ability getAbility() {
        return ability;
    }

    @Override
    public String toString() {
        return cardInstance.toString() +" performs "+ ability.toString();
    }
}
