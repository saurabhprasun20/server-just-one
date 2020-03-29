package ch.uzh.ifi.seal.soprafs20.rest.dto;

public final class UserAuthDTO {
    private String token;

    public UserAuthDTO(String token){
        this.token = token;
    }
    public String getToken() {
        return token;
    }

}
