package com.inftga.gamematch.core.deck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeckTest {



    @Test
    void DeckTest(){
        Deck d = DeckHelper.generateTestDeck();
        assertNotNull(d);
    }


}
