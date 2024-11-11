package com.example.lab7.beans;

import com.example.lab7.cdi.logging.LogExecutionTime;
import com.example.lab7.service.EvaluationService;
import com.example.lab7.entity.Evaluation;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@RequestScoped
@LogExecutionTime
public class EvaluationBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private EvaluationService evaluationService;

    private Evaluation evaluation = new Evaluation();

    @RolesAllowed({"Student"})
    @Transactional
    public void submitEvaluation() {
        evaluationService.submitEvaluation(evaluation);
    }

    @RolesAllowed({"Teacher"})
    @Transactional
    public List<Evaluation> getTeacherEvaluations(String teacherId) {
        return entityManager.createQuery("SELECT e FROM Evaluation e WHERE e.id = :teacherId", Evaluation.class)
                .setParameter("teacherId", teacherId)
                .getResultList();
    }

    @RolesAllowed({"Admin"})
    @Transactional
    public List<Evaluation> getAllEvaluations() {
        return entityManager.createQuery("SELECT e FROM Evaluation e", Evaluation.class)
                .getResultList();
    }
}