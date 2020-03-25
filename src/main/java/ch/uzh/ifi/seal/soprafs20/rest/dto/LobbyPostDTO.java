package ch.uzh.ifi.seal.soprafs20.rest.dto;

import java.util.ArrayList;

public class LobbyPostDTO {

    private String name;
    private Long hostPlayerId;
    private ArrayList<Long> playerIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long gethostPlayerId() {
        return hostPlayerId;
    }

    public void sethostPlayerId(Long hostPlayerId) {
        this.hostPlayerId = hostPlayerId;
    }

    public ArrayList<Long> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(ArrayList<Long> playerIds) {
        this.playerIds = playerIds;
    }
}
