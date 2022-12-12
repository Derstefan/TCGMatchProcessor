package com.inftga.gamematch.match;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardHelper;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.deck.Deck;
import com.inftga.gamematch.core.deck.DeckHelper;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.Field;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.player.Player;

import java.util.ArrayList;

public class MatchHelper {

    public static Match generateTestMatch(){
        Player p1 = new Player(EPlayer.P1, DeckHelper.generateTestDeck(),"hans");
        Player p2 = new Player(EPlayer.P2, DeckHelper.generateTestDeck(),"peter");

        Match match = new Match(p1,p2);


        Field f = match.getField();

        EPlayer first = match.getTurn();
        Pos pos1 =new Pos(first, ELineType.FRONT,0);
        match.placeCard(match.getHand(first).getCard(0),pos1);
        CardInstance c1 = f.getCardAt(pos1).get();

        match.endTurn();
        System.out.println(c1.getCard().getAbili().getAll().size());
        System.out.println(c1.getPossibleAbili().getAll().size());

        EPlayer next = first.getEnemy();
        Pos pos2 =new Pos(next, ELineType.FRONT,0);
        match.placeCard(match.getHand(next).getCard(0),pos2);
        CardInstance c2 = f.getCardAt(pos2).get();
        match.endTurn();

        return match;
    }
}
