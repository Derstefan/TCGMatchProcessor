package com.inftga.gamematch.core.card.ability;

import com.inftga.gamematch.core.card.ability.attack.AttackPlayerAbility;
import com.inftga.gamematch.core.card.ability.attack.MeleeAttackAbility;
import com.inftga.gamematch.core.card.ability.attack.RangedAttackAbility;
import com.inftga.gamematch.core.card.ability.movement.MoveTopAbility;
import com.inftga.gamematch.core.field.ELineType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Abilities {

    public static final Ability MOVE_TOP = new MoveTopAbility();
    public static final Ability MOVE_DOWN = new MoveTopAbility();
    public static final Ability MOVE_FRONT = new MoveTopAbility();
    public static final Ability MOVE_BACK = new MoveTopAbility();
    public static final AbilityCollector MOVE = new AbilityCollector(MOVE_TOP,MOVE_BACK,MOVE_DOWN,MOVE_FRONT);



    public static final Ability MELEE_ATTACK_FRONT = new MeleeAttackAbility(ELineType.FRONT);
    public static final Ability MELEE_ATTACK_BACK = new MeleeAttackAbility(ELineType.BACK);
    public static final AbilityCollector MELEE_ATTACK = new AbilityCollector(MELEE_ATTACK_FRONT,MELEE_ATTACK_BACK);


    public static final Ability RANGED_ATTACK_FRONT = new RangedAttackAbility(ELineType.FRONT);
    public static final Ability RANGED_ATTACK_BACK = new RangedAttackAbility(ELineType.BACK);
    public static final AbilityCollector RANGED_ATTACK = new AbilityCollector(RANGED_ATTACK_FRONT,RANGED_ATTACK_BACK);


    public static final Ability ATTACK_PLAYER = new AttackPlayerAbility();

    public static final Object getByName(String name) throws NoSuchFieldException, IllegalAccessException {
        Class a = Abilities.class;
        Object obj = null;
        return a.getField(name).get(obj);
    }

    public static final ArrayList<Object> getAll() throws IllegalAccessException {
        Class a = Abilities.class;
        Field[] fields = a.getFields();
        ArrayList<Object> objects =new ArrayList<>();

        for(Field f:fields){
            Object obj = null;
            objects.add(f.get(obj));
        }
        return objects;
    }
}
