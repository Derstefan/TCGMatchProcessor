package com.inftga.gamematch.core.card.ability;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.state.MatchState;

public abstract class Ability {
    public abstract boolean canPerform(MatchState state, CardInstance card);
    public abstract void perform(MatchState state, CardInstance card);

    public abstract String toString();
}
