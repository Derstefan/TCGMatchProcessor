package com.inftga.gamematch.core.card.origin;

import com.inftga.gamematch.core.card.ability.Abilities;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.AbilityCollector;
import com.inftga.gamematch.core.card.ability.attack.AttackPlayerAbility;
import com.inftga.gamematch.core.card.ability.attack.MeleeAttackAbility;
import com.inftga.gamematch.core.card.ability.attack.RangedAttackAbility;
import com.inftga.gamematch.core.card.ability.movement.*;
import com.inftga.gamematch.core.card.attribute.AttributCollector;
import com.inftga.gamematch.core.card.attribute.Attribute;
import com.inftga.gamematch.core.card.attribute.Attributes;
import com.inftga.gamematch.core.card.attribute.EAttribute;
import com.inftga.gamematch.core.card.stats.Stats;
import com.inftga.gamematch.core.field.ELineType;

import java.util.ArrayList;
import java.util.UUID;

public class CardBuilder {



/*    {
        UUID:"",
        name:"",
        owner?:,
        stats: {
            maxLife:
            demage:
            magicDemage:
            armor:
            magicAmor:
            costs:
        }
        attr : ["ORC"]
        abilities: ["MOVE","MELEE_ATTACK"]
    }*/

    public static Card getCardFromJSON(String json){
        return null;
    }


    public static Card getSimpleArcher(){

        Stats stats = new Stats(5,0,0,2,0,3);

        AttributCollector attr = new AttributCollector();

        AbilityCollector abili = new AbilityCollector();
        abili.add(Abilities.MOVE);
        abili.add(Abilities.RANGED_ATTACK);
        abili.add(Abilities.ATTACK_PLAYER);

        Card card = new Card(UUID.randomUUID(),"archer"+Math.round(Math.random()*100),stats,attr,abili);

        return card;
    }

    public static Card getSimpleWarrior(){

        Stats stats = new Stats(6,1,0,3,0,2);

        AttributCollector attr = new AttributCollector();

        AbilityCollector abili = new AbilityCollector();
        abili.add(Abilities.MOVE);
        abili.add(Abilities.MELEE_ATTACK);
        abili.add(Abilities.ATTACK_PLAYER);

        Card card = new Card(UUID.randomUUID(),"warrior"+Math.round(Math.random()*100),stats,attr,abili);

        return card;
    }

    public static Card getSimpleFarm(){

        Stats stats = new Stats(8,0,0,0,0,2);

        AttributCollector attr = new AttributCollector();
        attr.add(Attributes.EARN1);

        Card card = new Card(UUID.randomUUID(),"farm"+Math.round(Math.random()*100),stats,attr);

        return card;
    }

    public static Card getRandomCard(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(getSimpleArcher());
        cards.add(getSimpleWarrior());
        cards.add(getSimpleFarm());
        int index = (int) Math.round(Math.random()*2);
        return cards.get(index);
    }


}
