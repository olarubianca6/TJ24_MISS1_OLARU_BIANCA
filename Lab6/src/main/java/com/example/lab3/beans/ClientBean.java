package com.example.lab3.beans;

import com.example.lab3.entities.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ClientBean {
    private Client client = new Client();
    private List<Client> clients;

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab3PU");
    private EntityManager em = emf.createEntityManager();

    public void save() {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        client = new Client();
        loadClients();
    }

    public void loadClients() {
        clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Client clientToDelete = em.find(Client.class, id);
        if (clientToDelete != null) {
            em.remove(clientToDelete);
        }
        em.getTransaction().commit();
        loadClients();
    }

    public List<Client> getClients() {
        return clients;
    }

}