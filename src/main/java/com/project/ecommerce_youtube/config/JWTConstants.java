package com.project.ecommerce_youtube.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JWTConstants {
    // Secret key for signing and verifying JWT
    public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256) ; // Replace with your actual secret key

    // Token prefix
    public static final String TOKEN_PREFIX = "Bearer ";

    // Header key for sending the token in the request
    public static final String AUTH_HEADER = "Authorization";
}

