package com.vincenzo.d5w3u5.service;

import com.vincenzo.d5w3u5.entity.Utente;
import com.vincenzo.d5w3u5.exceptions.UnauthorizedException;
import com.vincenzo.d5w3u5.payload.AuthRequest;
import com.vincenzo.d5w3u5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ServiceUtente usersService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;
    public String authenticateUserAndGenerateToken(AuthRequest bodyRichiesta) {
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'utente
        Optional<Utente> userOptional = this.usersService.findUserByEmail(bodyRichiesta.getEmail());

        // 1.2 Verifico se l'utente è presente nell'Optional e ottengo l'oggetto Utente
        Utente user = userOptional.orElseThrow(() -> new UnauthorizedException("Utente non trovato per l'email specificata!"));

        // 1.3 Verifico se la password combacia con quella ricevuta nel bodyRichiesta
        if (bcrypt.matches(bodyRichiesta.getPassword(), user.getPassword())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(user);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }
    }

}
