package com.vincenzo.d5w3u5.payload;


public class DTOUtente {
    public enum Role {
        USER, ORGANIZER, ADMIN
    }


    private Role role;

    private String username;
    private String password;
    private String email;

    public DTOUtente(Role role, String username, String password, String email) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


