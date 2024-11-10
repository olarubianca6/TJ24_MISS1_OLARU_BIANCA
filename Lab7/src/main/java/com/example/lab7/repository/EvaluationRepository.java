package com.example.lab7.repository;

import com.example.lab7.entity.Evaluation;
import com.example.lab7.entity.Teacher;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EvaluationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Evaluation evaluation) {
        entityManager.persist(evaluation);
    }

    public List<Evaluation> findByTeacher(Teacher teacher) {
        return entityManager.createQuery("SELECT e FROM Evaluation e WHERE e.teacher = :teacher", Evaluation.class)
                .setParameter("teacher", teacher)
                .getResultList();
    }

    public List<Evaluation> findAll() {
        return entityManager.createQuery("SELECT e FROM Evaluation e", Evaluation.class).getResultList();
    }
}
