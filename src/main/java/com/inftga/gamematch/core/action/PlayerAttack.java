package com.inftga.gamematch.core.action;

import com.inftga.gamematch.core.card.CardInstance;
import com.inftga.gamematch.core.player.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerAttack {

    private CardInstance attacker;
    private Player player;

    public PlayerAttack(CardInstance attacker, Player player) {
        this.attacker = attacker;
        this.player = player;
    }

    public void apply(){
        if(attacker.getStats().getDemage()==0 && attacker.getStats().getMagicDemage()==0){
            log.warn("card can not attack without demage");
            return;
        }
        int demage = -1;
        if(attacker.getStats().getDemage()>=attacker.getStats().getMagicDemage()){
            //attacker is physical demage dealer
            demage = attacker.getStats().getDemage();
            player.demage(demage);
        }else if(attacker.getStats().getDemage()<attacker.getStats().getMagicDemage()){
            //attacker is magical demage dealer
            demage = attacker.getStats().getMagicDemage();
            player.demage(demage);
        }
        log.info(attacker.getCard().getName() + " attacks " + player.getName() + " with total amount of " + demage + " demage");
    }
}
