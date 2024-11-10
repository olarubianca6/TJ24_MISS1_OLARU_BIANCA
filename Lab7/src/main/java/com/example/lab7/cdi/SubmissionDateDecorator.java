package com.example.lab7.cdi;

import com.example.lab7.service.EvaluationService;
import com.example.lab7.entity.Evaluation;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Decorator
public class SubmissionDateDecorator extends EvaluationService {

    @Inject
    @Delegate
    private EvaluationService evaluationService;

    @Override
    @Transactional
    public void submitEvaluation(Evaluation evaluation) {
        evaluation.setTimestamp(System.currentTimeMillis());
        evaluationService.submitEvaluation(evaluation);
    }
}
