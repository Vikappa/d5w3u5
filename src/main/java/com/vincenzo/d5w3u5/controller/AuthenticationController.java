package com.vincenzo.d5w3u5.controller;

import com.vincenzo.d5w3u5.payload.AuthRequest;
import com.vincenzo.d5w3u5.payload.AuthResponse;
import com.vincenzo.d5w3u5.service.AuthService;
import com.vincenzo.d5w3u5.service.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ServiceUtente userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest payloadLogin){
        return new AuthResponse(this.authService.authenticateUserAndGenerateToken(payloadLogin));
    }
}
