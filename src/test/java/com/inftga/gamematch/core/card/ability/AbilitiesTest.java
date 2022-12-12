package com.inftga.gamematch.core.card.ability;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AbilitiesTest {

    @Test
    void AbilitiesTest() throws NoSuchFieldException, IllegalAccessException {
        Object obj = null;
        Ability a = (Ability)(Abilities.getByName("ATTACK_PLAYER"));
        System.out.println(a.toString());

        ArrayList<Object> objects = new ArrayList<>();
        objects = (Abilities.getAll());
        System.out.println(objects.size() + " fields");
    }
}
