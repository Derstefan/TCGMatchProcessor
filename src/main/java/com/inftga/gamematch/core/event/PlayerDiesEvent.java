package com.inftga.gamematch.core.event;

import com.inftga.gamematch.core.player.Player;

public class PlayerDiesEvent extends Event{

    private Player player;

    public PlayerDiesEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return player.getName() + " died";
    }
}
