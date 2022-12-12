package com.inftga.gamematch.core.card.ability.attack;

import com.inftga.gamematch.config.Config;
import com.inftga.gamematch.core.action.Attack;
import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.ability.Ability;
import com.inftga.gamematch.core.card.ability.helper.Conditions;
import com.inftga.gamematch.core.event.AttackEvent;
import com.inftga.gamematch.core.field.ELineType;
import com.inftga.gamematch.core.field.position.Pos;
import com.inftga.gamematch.core.field.position.Vec;
import com.inftga.gamematch.core.state.MatchState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RangedAttackAbility extends Ability {

    /**
     * Vector i=di, Line
     */
    private Vec enemyVec;

    public RangedAttackAbility(Vec enemyVec) {
        this.enemyVec = enemyVec;
    }

    public RangedAttackAbility(ELineType l, int i) {
        this.enemyVec = new Vec(l,i);
    }

    public RangedAttackAbility(ELineType l) {
        this.enemyVec = new Vec(l,0);
    }

    private Pos computeEnemyPos(CardInstance card){
        if(card.getPos().getI()-enemyVec.getI()<0 || card.getPos().getI()-enemyVec.getI()> Config.LINE_SIZE-1){
            throw new IllegalArgumentException("Position doesnt exist");
        }
        Pos pos = new Pos(enemyVec.getL(),card.getPos().getP().getEnemy(),card.getPos().getI()-enemyVec.getI());
        return pos;
    }

    @Override
    public boolean canPerform(MatchState state, CardInstance card) {
        if(Conditions.CANT_PERFORM(state,card)){
            return false;
        }
        if (!Conditions.CAN_ATTACK(card)) {
            return false;
        }

        Pos enemyPos = computeEnemyPos(card);
        if(!state.getField().isFree(enemyPos)){
            return true;
        }
        return false;
    }

    @Override
    public void perform(MatchState state, CardInstance card) {
        if(canPerform(state,card)){
            CardInstance enemy = state.getCardAt(computeEnemyPos(card)).get();
            Attack attack = new Attack(card,enemy);
            attack.apply();

            if(!enemy.isAlife()){
                state.putCardOnGraveyard(enemy);
            }
        }
    }

    @Override
    public String toString() {
        return "Ranged attack";
    }
}
