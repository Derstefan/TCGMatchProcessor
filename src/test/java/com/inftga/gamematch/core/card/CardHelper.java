package com.inftga.gamematch.core.card;

import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.card.origin.CardBuilder;

public class CardHelper {

    public static Card getArcherCard(){
        return CardBuilder.getSimpleArcher();
    }

    public static Card getWarriorCard(){
        return CardBuilder.getSimpleWarrior();
    }


}
