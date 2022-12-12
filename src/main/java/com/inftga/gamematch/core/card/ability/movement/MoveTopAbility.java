package com.inftga.gamematch.core.card.ability.movement;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.helper.Conditions;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.state.MatchState;

public class MoveTopAbility extends MoveAbility {
    @Override
    public boolean canPerform(MatchState state, CardInstance card) {
        if(Conditions.CANT_PERFORM(state,card)){
            return false;
        }
        if(!card.getPos().canMoveTop()){
            return false;
        }
        if(Conditions.CAN_MOVE(state,card)){
            Pos top = card.getPos().top();
            return state.getField().isFree(top);
        }
        return true;
    }

    @Override
    public void perform(MatchState state, CardInstance card) {
        if(canPerform(state,card)){
            state.moveCard(card,card.getPos().top());
        }
    }

    @Override
    public String toString() {
        return "move top";
    }
}
