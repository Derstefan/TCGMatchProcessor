package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.card.CardInstance;

public class DiedEvent extends Event{

    private CardInstance cardInstance;

    public DiedEvent(CardInstance cardInstance) {
        this.cardInstance = cardInstance;
    }

    public CardInstance getCardInstance() {
        return cardInstance;
    }

    @Override
    public String toString() {
        return cardInstance.toString() + " died";
    }
}
