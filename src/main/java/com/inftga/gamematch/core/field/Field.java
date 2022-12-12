package com.inftga.gamematch.core.field;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.EPlayer;

import java.util.ArrayList;
import java.util.Optional;

public class Field {

    private Line backLineP1 = new Line(ELineType.BACK, EPlayer.P1);
    private Line frontLineP1 = new Line(ELineType.FRONT, EPlayer.P1);

    private Line frontLineP2 = new Line(ELineType.FRONT, EPlayer.P2);
    private Line backLineP2 = new Line(ELineType.BACK, EPlayer.P2);



    private Line getLine(ELineType l, EPlayer p){
        if(EPlayer.P1.equals(p)){
            if(ELineType.BACK.equals(l)){
                return backLineP1;
            }
            return frontLineP1;
        }
        if(ELineType.BACK.equals(l)){
            return backLineP2;
        }
        return frontLineP2;
    }

    public boolean isFree(ELineType l, EPlayer p, int i){
        return getLine(l,p).getCardInstance(i).isEmpty();
    }

    public boolean isFree(Pos pos){

        return getLine(pos.getL(),pos.getP()).getCardInstance(pos.getI()).isEmpty();
    }

    public Optional<CardInstance> getCardAt(ELineType l, EPlayer p, int i){
        return getLine(l,p).getCardInstance(i);
    }

    public Optional<CardInstance> getCardAt(Pos pos){
        return getLine(pos.getL(),pos.getP()).getCardInstance(pos.getI());
    }


    public boolean putCardAt(CardInstance card, ELineType l, EPlayer p, int i){
        return getLine(l,p).putCard(i,card);
    }

    public boolean putCardAt(CardInstance card, Pos pos){
        card.setPos(pos);
        return getLine(pos.getL(),pos.getP()).putCard(pos.getI(),card);
    }

    public boolean removeCard(ELineType l, EPlayer p, int i){
        return getLine(l,p).removeCard(i);
    }

    public boolean removeCard(Pos pos){
        return getLine(pos.getL(),pos.getP()).removeCard(pos.getI());
    }

    private String posToString(Optional<CardInstance> opt){
        return opt.isPresent()?" "+opt.get().getCard().getName()+" ":" -" ;
    }

    public String toString(){
        String str = "";
        for(int i=0;i< Config.LINE_SIZE;i++){
            str+=posToString(backLineP1.getCardInstance(i));
            str+=posToString(frontLineP1.getCardInstance(i));
            str+=posToString(frontLineP2.getCardInstance(i));
            str+=posToString(backLineP2.getCardInstance(i));
            str+="\n";
        }
        return str;
    }

    public ArrayList<Pos> freePositions(EPlayer p){
        ArrayList<Pos> ps= new ArrayList<>();
        if(EPlayer.P1.equals(p))
        for(int i=0;i< Config.LINE_SIZE;i++){
            if(frontLineP1.getCardInstance(i).isEmpty()){
                ps.add(new Pos(ELineType.FRONT,p,i));
            }
            if(backLineP1.getCardInstance(i).isEmpty()){
                ps.add(new Pos(ELineType.BACK,p,i));
            }
        } else if(EPlayer.P2.equals(p))
            for(int i=0;i< Config.LINE_SIZE;i++){
                if(frontLineP2.getCardInstance(i).isEmpty()){
                    ps.add(new Pos(ELineType.FRONT,p,i));
                }
                if(backLineP2.getCardInstance(i).isEmpty()){
                    ps.add(new Pos(ELineType.BACK,p,i));
                }
            }
        return ps;
    }
}
