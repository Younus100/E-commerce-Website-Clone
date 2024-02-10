package com.project.ecommerce_youtube.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract the JWT token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            // Remove the "Bearer " prefix from the token
            String jwt = authorizationHeader.substring(7);

            try {
                SecretKey secretKey = JWTConstants.SECRET_KEY;
                Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authoroties"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // Handle token validation exception
                    throw  new BadCredentialsException("invlaid cresentials");
            }
        }
         filterChain.doFilter(request,response);
    }
}
