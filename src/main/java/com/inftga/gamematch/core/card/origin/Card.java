package com.inftga.gamematch.core.card.origin;

import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.AbilityCollector;
import com.inftga.gamematch.core.card.attribute.AttributCollector;
import com.inftga.gamematch.core.card.stats.Stats;

import java.util.UUID;

public class Card {

    private UUID id;

    private String name;

    private Stats stats;
    private AttributCollector attr;
    private AbilityCollector abili;



    public Card(UUID id, String name, Stats stats, AttributCollector attr, AbilityCollector abili) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.attr = attr;
        this.abili=abili;
    }

    public Card(UUID id, String name, Stats stats, AttributCollector attr) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.attr = attr;
        this.abili=new AbilityCollector();
    }

    public Card(UUID id, String name, Stats stats,AbilityCollector abili) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.attr = new AttributCollector();
        this.abili=abili;
    }

    public Card(UUID id, String name, Stats stats) {
        this.id = id;
        this.name = name;
        this.stats = stats;
        this.attr = new AttributCollector();
        this.abili=new AbilityCollector();
    }

    public Stats getStats() {
        return stats;
    }

    public AttributCollector getAttr() {
        return attr;
    }

    public AbilityCollector getAbili() {
        return abili;
    }

    public Ability getAbility(int index){
        return abili.getAbility(index);
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String toString(){
        return name;
    }
}
