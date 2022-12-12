package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.field.position.Pos;

public class PlaceCardEvent extends Event{

    private Card c;

    private Pos pos;

    public PlaceCardEvent(Card c, Pos pos){
        super();
        this.c = c;
        this.pos=pos;
    }


    @Override
    public String toString() {
        return c.toString() + " placed at " + pos.toString();
    }
}
