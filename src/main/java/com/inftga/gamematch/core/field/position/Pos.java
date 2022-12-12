package com.inftga.gamematch.core.field.position;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.player.EPlayer;

import java.util.Optional;

public class Pos {

    private ELineType l;
    private EPlayer p;
    private int i;


    public Pos(ELineType l, EPlayer p, int i) {
        this.l = l;
        this.p = p;
        this.i = i;
    }

    public Pos(EPlayer p, ELineType l, int i) {
        this.l = l;
        this.p = p;
        this.i = i;
    }

    public ELineType getL() {
        return l;
    }

    public EPlayer getP() {
        return p;
    }


    public int getI() {
        return i;
    }

    public boolean isFront(){
        return ELineType.FRONT.equals(l);
    }

    public boolean isBack(){
        return ELineType.BACK.equals(l);
    }


    public Pos top(){
        if(canMoveTop()){
            return new Pos(l,p,i-1);
        }
        return null;
    }

    public Pos down(){
        if(canMoveDown()){
            return new Pos(l,p,i+1);
        }
        return null;
    }

    public Pos front(){
        if(canMoveFront()){
            return new Pos(ELineType.FRONT,p,i);
        }
        return null;
    }

    public Pos back(){
        if(canMoveBack()){
            return new Pos(ELineType.BACK,p,i);
        }
        return null;
    }

    public boolean canMoveTop(){
        return i>0;
    }

    public boolean canMoveDown(){
        return i< Config.LINE_SIZE-1;
    }

    public boolean canMoveBack(){
        return ELineType.FRONT.equals(l);
    }

    public boolean canMoveFront(){
        return ELineType.BACK.equals(l);
    }

    public Pos enemySide(){
        Pos pos = new Pos(l,p.getEnemy(),i);
        return pos;
    }

    public String toString(){
        return "("+p+","+l +","+i+")";
    }
}
