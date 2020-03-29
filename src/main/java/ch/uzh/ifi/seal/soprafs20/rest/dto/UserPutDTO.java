package ch.uzh.ifi.seal.soprafs20.rest.dto;

public class UserPutDTO {

    private String name;
    private String password;
    private long invitation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getInvitation() {
        return invitation;
    }

    public void setInvitation(long invitation) {
        this.invitation = invitation;
    }
}
