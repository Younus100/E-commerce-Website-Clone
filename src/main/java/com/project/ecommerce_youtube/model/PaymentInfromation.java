package com.project.ecommerce_youtube.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;


public class PaymentInfromation {

    private String cardHolderName;

    private String cardNumber;

    private LocalDate expirationDate;

    private String cvv;

}
