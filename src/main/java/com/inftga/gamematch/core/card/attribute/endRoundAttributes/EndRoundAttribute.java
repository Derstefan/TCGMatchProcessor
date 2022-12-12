package com.inftga.gamematch.core.card.attribute.endRoundAttributes;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.attribute.Attribute;
import com.inftga.gamematch.core.card.attribute.EAttribute;
import com.inftga.gamematch.core.state.MatchState;

public abstract class EndRoundAttribute extends Attribute {

    public EndRoundAttribute(EAttribute type) {
        super(type);
    }

    public abstract void nextRound(MatchState state, CardInstance cardInstance);
}
