package com.vincenzo.d5w3u5.security;

import com.vincenzo.d5w3u5.entity.Utente;
import com.vincenzo.d5w3u5.exceptions.UnauthorizedException;
import com.vincenzo.d5w3u5.service.ServiceUtente;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private ServiceUtente usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token non presente o non valido.");
        }

        String accessToken = authHeader.substring(7);
        jwtTools.verifyToken(accessToken);

        Long userId = jwtTools.extractIdFromToken(accessToken);
        Optional<Utente> userOptional = usersService.findUserById(userId);

        if (!userOptional.isPresent()) {
            throw new UnauthorizedException("Utente non trovato.");
        }

        Utente currentUser = userOptional.get();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                currentUser, null, currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match("/auth/**", request.getServletPath());
    }
}
