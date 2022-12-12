package com.inftga.gamematch.core.card.attribute;

public class Attribute {
    private EAttribute type;

    public Attribute(EAttribute type) {
        this.type = type;
    }

    public EAttribute getType() {
        return type;
    }

    public Attribute clone(){
        Attribute a = new Attribute(type);
        return a;
    }
}
