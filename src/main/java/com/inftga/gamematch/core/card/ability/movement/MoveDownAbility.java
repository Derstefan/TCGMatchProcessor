package com.inftga.gamematch.core.card.ability.movement;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.helper.Conditions;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.state.MatchState;

public class MoveDownAbility extends MoveAbility{

    @Override
    public boolean canPerform(MatchState state, CardInstance card) {
        if(Conditions.CANT_PERFORM(state,card)){
            return false;
        }
        if(!card.getPos().canMoveDown()){
            return false;
        }
        if(Conditions.CAN_MOVE(state,card)){
            Pos down = card.getPos().down();
            return state.getField().isFree(down);
        }
        return true;
    }

    @Override
    public void perform(MatchState state, CardInstance card) {
        if(canPerform(state,card)){
            state.moveCard(card,card.getPos().down());
        }
    }

    @Override
    public String toString() {
        return "move down";
    }
}
