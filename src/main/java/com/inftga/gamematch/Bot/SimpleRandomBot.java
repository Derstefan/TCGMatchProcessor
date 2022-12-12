package com.inftga.gamematch.Bot;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.origin.Card;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.state.MatchState;
import com.inftga.gamematch.match.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SimpleRandomBot {


    public static void doDraw(Match match){
        System.out.println("----------------\n"+match.getPlayer(match.getTurn()) +"s turn:");
        placeRandomCard(match);
        doRandomAbility(match);
        System.out.println("----------------------------\n");

    }

    public static void doRandomAbility(Match match){
        EPlayer p = match.getTurn();

        ArrayList<CardInstance> cards = match.getAllCardInstancesOf(p);
        if(cards.isEmpty()){
            match.endTurn();
            return;
        }
        HashMap<CardInstance, ArrayList<Ability>> map = new HashMap<>();
        boolean noAbili = true;
        for(CardInstance c:cards){
            if(!c.getPossibleAbili().getAll().isEmpty()){
                map.put(c,c.getPossibleAbili().getAll());
                noAbili=false;
            }
        }

        if(noAbili){
            match.endTurn();
            return;
        }

        int cIndex = (int) Math.round(Math.random()*(map.keySet().toArray().length-1));
        CardInstance c = (CardInstance) map.keySet().toArray()[cIndex];

        /*System.out.println(c.toString() + " can do: ");
        System.out.println(c.getPossibleAbili().toString());*/


        int aIndex = (int) Math.round(Math.random()*(map.get(c).size()-1));
        Ability a = map.get(c).get(aIndex);
        /*System.out.print(c.toString() + " -> ");
        System.out.println(a.toString());*/
        match.doAbility(c,a);
    }

    public static void placeRandomCard(Match match){
        EPlayer p = match.getTurn();
        ArrayList<Card> placeableCards = new ArrayList<>();
        for(Card c: match.getHand(p).getCards()){
            if(match.getPlayer(p).canPay(c)){
                placeableCards.add(c);
            }
        }

        ArrayList<Pos> ps = match.getField().freePositions(p);
        if(placeableCards.size()==0 || ps.size()==0){
            return;
        }

        int cIndex = (int) Math.round(Math.random()*(placeableCards.size()-1));

        int pIndex = (int) Math.round(Math.random()*(ps.size()-1));

        Card c =  placeableCards.get(cIndex);
        Pos pos = ps.get(pIndex);

        match.placeCard(c,pos);
        //System.out.println("placed " +c.toString() + " at " + pos.toString());
    }
}
