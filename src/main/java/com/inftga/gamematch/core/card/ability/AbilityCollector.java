package com.inftga.gamematch.core.card.ability;

import java.util.ArrayList;
import java.util.HashSet;

public class AbilityCollector {

    private ArrayList<Ability> abilities = new ArrayList<>();

    public AbilityCollector(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public AbilityCollector(){

    }

    public AbilityCollector(Ability ... abilities){
        for(Ability a: abilities){
            this.abilities.add(a);
        }

    }

    public void add(Ability a){
        abilities.add(a);
    }

    public void add(AbilityCollector abili){
        abilities.addAll(abili.getAll());
    }


    public void remove(Ability a){
        abilities.remove(a);
    }

    public Ability getAbility(int index){
        return abilities.get(index);
    }

    public boolean contains(Ability a){
        for(Ability b:abilities){
            if(b.equals(a)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Ability> getAll(){
        return abilities;
    }


    public void addAll(AbilityCollector abili){
        abilities.addAll(abili.getAll());
    }
    public void removeAll(){
        abilities = new ArrayList<>();
    }

    public String toString(){
        String str = "";
        for(Ability a: abilities){
            str+= a.toString() + "\n";
        }
        return str;
    }
}
