package com.example.lab7.cdi;

import jakarta.enterprise.inject.Produces;
import jakarta.transaction.Transactional;
import java.util.UUID;

public class RegistrationNumberGenerator {

    @Produces
    @Transactional
    public String generateRegistrationNumber() {
        return UUID.randomUUID().toString();
    }
}
