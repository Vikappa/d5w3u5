package com.vincenzo.d5w3u5.entity;

import com.vincenzo.d5w3u5.enumerator.Ruolo;
import com.vincenzo.d5w3u5.payload.DTOUtente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Utente {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Ruolo role;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;

    public Utente() {
    }

    public Utente(Ruolo role, String username, String password, String email) {
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
