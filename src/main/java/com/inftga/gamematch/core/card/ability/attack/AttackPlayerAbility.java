package com.inftga.gamematch.core.card.ability.attack;

import com.inftga.gamematch.core.action.PlayerAttack;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.helper.Conditions;
import com.inftga.gamematch.core.event.PlayerDiesEvent;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.player.Player;
import com.inftga.gamematch.core.state.MatchState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttackPlayerAbility extends Ability {

    @Override
    public boolean canPerform(MatchState state, CardInstance card) {
        if(Conditions.CANT_PERFORM(state,card)){
           return false;
        }
        if(!Conditions.CAN_ATTACK(card)){
            return false;
        }
        Pos cardPos = card.getPos();
        Pos pos1 = new Pos(ELineType.FRONT,cardPos.getP().getEnemy(),cardPos.getI());
        Pos pos2 = new Pos(ELineType.BACK,cardPos.getP().getEnemy(),cardPos.getI());

        if(state.getField().isFree(pos1) && state.getField().isFree(pos2)){
            return true;
        }
        return false;
    }

    @Override
    public void perform(MatchState state, CardInstance card) {
        if(canPerform(state,card)){
            Player enemy = state.getPlayer(card.getOwner().getEnemy());
            PlayerAttack attack = new PlayerAttack(card,enemy);
            attack.apply();

            if(enemy.isDeath()){
                state.addEvent(new PlayerDiesEvent(enemy));
                state.checkIfGameEnd();
            }
        }
    }

    @Override
    public String toString() {
        return "Attack Player";
    }
}
