package com.inftga.gamematch.match;

import com.inftga.gamematch.Bot.SimpleRandomBot;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.deck.DeckHelper;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.Field;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class MatchTest {

    @Test
    void TestMatch(){

        Player p1 = new Player(EPlayer.P1, DeckHelper.generateTestDeck(),"hans");
        Player p2 = new Player(EPlayer.P2, DeckHelper.generateTestDeck(),"peter");

        Match match = new Match(p1,p2);
        assertNotNull(match);
        Field f = match.getField();

        System.out.println(match.toString());

/*        EPlayer first = match.getTurn();
        Pos pos1 =new Pos(first, ELineType.FRONT,0);
        match.placeCard(match.getHand(first).getCard(0),pos1);
        CardInstance c1 = f.getCardAt(pos1).get();

        match.endTurn();
        System.out.println(match.toString());

        EPlayer next = first.getEnemy();
        Pos pos2 =new Pos(next, ELineType.FRONT,0);
        match.placeCard(match.getHand(next).getCard(0),pos2);
        CardInstance c2 = f.getCardAt(pos2).get();
        match.endTurn();

        System.out.println(match.toString());*/

        for(int i=0;i<200;i++){
            if(!match.matchEnded()){
                SimpleRandomBot.doDraw(match);
                System.out.println(match.toString());
            }
        }
        System.out.println("---------------------> Match ended");




    }
}
