package com.inftga.gamematch.matchmanager;

import com.inftga.gamematch.core.player.Player;
import com.inftga.gamematch.match.Match;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Component
public class MatchManager {

    private ArrayList<Match> matches = new ArrayList<>();

    public void startMatch(){
        Match match = new Match();
        matches.add(match);
    }

    public void joinMatch(UUID uuid, Player p){
        Optional<Match> m = getMatchByUUID(uuid);
        if(m.isPresent()){
            m.get().join(p);
        }
    }

    public Optional<Match> getMatchByUUID(UUID uuid){
        for(Match m:matches){
            if(m.getMatchId().equals(uuid)){
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

}
