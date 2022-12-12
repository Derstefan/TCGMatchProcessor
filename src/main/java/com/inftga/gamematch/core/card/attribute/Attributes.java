package com.inftga.gamematch.core.card.attribute;

import com.inftga.gamematch.core.card.attribute.endRoundAttributes.EarnGoldAttribute;

public class Attributes {

    /**
     * static Attributes, not fro all Attributes!!
     */
    public static final Attribute IMMOBIL = new Attribute(EAttribute.immobil);
    public static final Attribute RANGED = new Attribute(EAttribute.ranged);
    public static final Attribute STUNNED = new Attribute(EAttribute.stunned);

    public static final Attribute EARN1 = new EarnGoldAttribute(1);
    public static final Attribute EARN2 = new EarnGoldAttribute(2);
    public static final Attribute EARN3 = new EarnGoldAttribute(3);
}
