package com.vincenzo.d5w3u5.payload;

import com.vincenzo.d5w3u5.enumerator.Ruolo;
import lombok.Getter;
import lombok.Setter;

public class DTOUtente {


    private Ruolo role;

    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String email;

    public DTOUtente(Ruolo role, String username, String password, String email) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Ruolo getRuolo() {
        return role;
    }

    public void setRuolo(Ruolo role) {
        this.role = role;
    }

}


