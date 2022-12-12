package com.inftga.gamematch.core.field;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.player.EPlayer;
import com.inftga.gamematch.core.state.MatchState;

import java.util.ArrayList;
import java.util.Optional;

public class Line {
    private ELineType type;
    private EPlayer player;

    private ArrayList<Optional<CardInstance>> cards = new ArrayList<>();

    public Line( ELineType type, EPlayer player){
        this.type = type;
        this.player = player;
        initLine();
    }

    private void initLine(){
        // fill line with empty Optionals
        for(int i=0;i<Config.LINE_SIZE;i++){
            cards.add(Optional.empty());
        }
    }

    public Optional<CardInstance> getCardInstance(int index){
        return cards.get(index);
    }

    public boolean putCard(int index, CardInstance card){
        if(cards.get(index).isEmpty()){
            cards.set(index,Optional.of(card));
            return true;
        }
        return false;
    }



    public boolean removeCard(int index){
        if(!cards.get(index).isEmpty()){
            cards.set(index,Optional.empty());
            return true;
        }
        return false;
    }



    public ELineType getType() {
        return type;
    }

    public EPlayer getPlayer() {
        return player;
    }

    public void computePossibleAbilities(MatchState state){
        for(Optional<CardInstance> c:cards){
            if(c.isPresent()){
                c.get().computePossibleAbilities(state);
            }
        }
    }
}
