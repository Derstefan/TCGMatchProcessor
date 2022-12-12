package com.inftga.gamematch.core.player;

import com.inftga.gamematch.core.deck.DeckHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {


    @Test
    void playerTest(){
        Player p1 = new Player(EPlayer.P1, DeckHelper.generateTestDeck(),"hans");
        assertNotNull(p1);
    }
}
