package com.inftga.gamematch.core.field.position;

import com.inftga.gamematch.core.field.ELineType;

public class Vec {

    private ELineType l;
    private int i;


    public Vec(ELineType l, int i) {
        this.l = l;
        this.i = i;
    }

    public ELineType getL() {
        return l;
    }

    public int getI() {
        return i;
    }
}
