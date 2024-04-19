package com.vincenzo.d5w3u5.service;

import com.vincenzo.d5w3u5.exceptions.UserNotFoundException;
import com.vincenzo.d5w3u5.repository.UtenteRepository;
import com.vincenzo.d5w3u5.entity.Utente;
import com.vincenzo.d5w3u5.payload.DTOUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUtente {

    private final UtenteRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServiceUtente(UtenteRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente registerNewUserAccount(DTOUtente userDto) {
        Utente newUser = new Utente();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setEmail(userDto.getEmail());
        newUser.setRuolo(userDto.getRuolo());
        return userRepository.save(newUser);
    }

    public List<Utente> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Utente> findUserById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<Utente> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
