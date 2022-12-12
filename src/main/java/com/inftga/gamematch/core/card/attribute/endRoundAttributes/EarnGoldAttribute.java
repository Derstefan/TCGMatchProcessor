package com.inftga.gamematch.core.card.attribute.endRoundAttributes;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.card.attribute.EAttribute;
import com.inftga.gamematch.core.state.MatchState;

public class EarnGoldAttribute extends EndRoundAttribute {

    private final int amount;

    public EarnGoldAttribute(int amount) {
        super(EAttribute.earn);
        this.amount=amount;
    }

    @Override
    public void nextRound(MatchState state, CardInstance cardInstance) {
        state.getPlayer(cardInstance.getOwner()).earn(amount);
    }
}
