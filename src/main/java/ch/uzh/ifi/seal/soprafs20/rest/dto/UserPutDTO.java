package ch.uzh.ifi.seal.soprafs20.rest.dto;

public class UserPutDTO {

    private String username;
    private long invitation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getInvitation() {
        return invitation;
    }

    public void setInvitation(long invitation) {
        this.invitation = invitation;
    }
}
