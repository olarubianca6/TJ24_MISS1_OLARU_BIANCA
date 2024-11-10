package com.example.lab7.service;

import com.example.lab7.entity.Evaluation;
import com.example.lab7.repository.EvaluationRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class EvaluationService {

    @Inject
    private EvaluationRepository evaluationRepository;

    @Transactional
    public void submitEvaluation(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }
}
