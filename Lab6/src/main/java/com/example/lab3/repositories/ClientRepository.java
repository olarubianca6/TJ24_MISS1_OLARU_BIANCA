package com.example.lab3.repositories;

import com.example.lab3.entities.Client;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Client addClient(Client client) {
        entityManager.persist(client);
        return client;
    }

    public Client findClientById(Long clientId) {
        return entityManager.find(Client.class, clientId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeClient(Long clientId) {
        Client client = entityManager.find(Client.class, clientId);
        if (client != null) {
            entityManager.remove(client);
        }
    }}
