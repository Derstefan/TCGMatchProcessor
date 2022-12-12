package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.player.EPlayer;

public class TakeCardEvent extends Event{

    private EPlayer p;
    private Card c;

    public TakeCardEvent(EPlayer p, Card c){
        super();
        this.p=p;
        this.c=c;
    }


    @Override
    public String toString() {
        return p + " draws new Card: " + c.toString();
    }
}
