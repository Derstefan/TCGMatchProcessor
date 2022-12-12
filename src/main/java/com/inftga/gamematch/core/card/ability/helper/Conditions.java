package com.inftga.gamematch.core.card.ability.helper;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.attribute.EAttribute;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.state.MatchState;

public class Conditions {

    public static final boolean IS_FREE_POSITION(MatchState state, Pos pos){
        return state.getField().isFree(pos);
    }

    public static final boolean CARD_IS_ON_Field(MatchState state, CardInstance card){
        return state.cardIsOnField(card);
    }

    public static final boolean IS_FRONTLINE(CardInstance card){return card.getPos().isFront(); }
    public static final boolean IS_BACKLINE(CardInstance card){return card.getPos().isBack(); }
    public static final boolean CAN_ATTACK(CardInstance card){return !card.is(EAttribute.stunned);}
    public static final boolean CAN_MOVE(MatchState state,CardInstance cardInstance){

        return !cardInstance.is(EAttribute.immobil) && state.getPlayer(cardInstance.getOwner()).canMove();
    }

    public static final boolean CANT_PERFORM(MatchState state,CardInstance cardInstance){
        return cardInstance.getOwner().equals(state.getTurn())&& !state.cardIsOnField(cardInstance);
    }
}
