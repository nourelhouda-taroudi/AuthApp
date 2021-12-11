package com.devoir.auth.filters;


import com.devoir.auth.exceptions.InvalidJwtTokenException;
import com.devoir.auth.models.User;
import com.devoir.auth.services.UserJpaRepository;
import com.devoir.auth.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserJpaRepository userRepository;
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // The provided token
        String token = authorizationHeader.replace("Bearer ", "");

        try {
            // The token subject
            Long utilisateurId = Long.parseLong(jwtUtil.extractSubject(token));

            // Getting the user
            User utilisateur = userRepository.findById(utilisateurId)
                    .orElseThrow(InvalidJwtTokenException::new);

            if (! jwtUtil.validateToken(token , utilisateur)) {
                throw new InvalidJwtTokenException("Token expiré.");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                utilisateur,
                null,
                    null
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            resolver.resolveException(
                request,
                response,
                null,
                new InvalidJwtTokenException("Le token fourni n'est pas valable.")
            );
        }
    }
}
