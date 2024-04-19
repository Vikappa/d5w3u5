package com.vincenzo.d5w3u5.controller;

import com.vincenzo.d5w3u5.entity.Utente;
import com.vincenzo.d5w3u5.exceptions.UserNotFoundException;
import com.vincenzo.d5w3u5.payload.DTOUtente;
import com.vincenzo.d5w3u5.service.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")  // Cambiato da /registration a /users per generalità
public class UserRegistrationController {

    private final ServiceUtente userService;

    @Autowired
    public UserRegistrationController(ServiceUtente userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Utente> registerUser(@RequestBody DTOUtente userDto) {
        Utente registeredUser = userService.registerNewUserAccount(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getAllUsers() {
        List<Utente> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUserById(@PathVariable Long id) {
        Utente user = userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("Utente non trovato con id: " + id));
        return ResponseEntity.ok(user);
    }

}
