package com.inftga.gamematch.core.card;

import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.card.origin.CardBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardTest {


    @Test
    void CardTest(){
        Card c = CardBuilder.getSimpleArcher();
        CardInstance cInst = new CardInstance(c);
        assertNotNull(c);
        assertNotNull(cInst);
    }
}
