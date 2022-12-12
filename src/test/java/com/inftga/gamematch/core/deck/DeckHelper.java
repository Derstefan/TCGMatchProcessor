package com.inftga.gamematch.core.deck;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.CardHelper;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.card.origin.CardBuilder;

import java.util.ArrayList;

public class DeckHelper {

    public static Deck generateTestDeck(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i< Config.DECK_MIN_SIZE+3; i++){
            cards.add(CardBuilder.getRandomCard());
        }
        return new Deck(cards);
    }

}
