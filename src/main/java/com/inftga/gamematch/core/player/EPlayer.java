package com.inftga.gamematch.core.player;

public enum EPlayer {
    P1,P2;


    public EPlayer getEnemy(){
        if(EPlayer.P1.equals(this)){
            return EPlayer.P2;
        }
        return EPlayer.P1;
    }

    public static EPlayer randomPlayer(){
        if(Math.random()>0.5){
            return EPlayer.P1;
        }
        return EPlayer.P2;
    }
}
