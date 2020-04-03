package ch.uzh.ifi.seal.soprafs20.rest.dto;

import java.util.List;

public class GamePostDTO {

    private List<Long> playerIds;

    public List<Long> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Long> playerIds) {
        this.playerIds = playerIds;
    }
}
