package com.inftga.gamematch.core.card.attribute;

import java.util.HashSet;

public class AttributCollector {

    private HashSet<Attribute> attributes = new HashSet<>();

    public AttributCollector(HashSet<Attribute> attributes) {
        this.attributes = attributes;
    }

    public AttributCollector() {
    }

    public void add(Attribute a){
        attributes.add(a);
    }

    public void remove(Attribute a){
        attributes.remove(a);
    }

    public HashSet<Attribute> getAllAttributes() {
        return attributes;
    }

    public boolean contains(EAttribute a){
        for(Attribute b:attributes){
            if(b.getType().equals(a)){
                return true;
            }
        }
        return false;
    }

    public AttributCollector clone(){
        AttributCollector attr = new AttributCollector(attributes);
        return attr;
    }
}
