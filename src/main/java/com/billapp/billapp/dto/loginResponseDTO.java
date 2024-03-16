package com.billapp.billapp.dto;

import com.billapp.billapp.entities.ApplicationUser;

public class loginResponseDTO {
    private ApplicationUser user;
    private String jwt;

    public loginResponseDTO(){
        super();
    }
    public loginResponseDTO(ApplicationUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
