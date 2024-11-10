package com.example.lab7.beans;

import com.example.lab7.service.EvaluationService;
import com.example.lab7.entity.Evaluation;
import com.example.lab7.RoleBasedAccess;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EvaluationBean {

    @Inject
    private EvaluationService evaluationService;

    @Inject
    private RoleBasedAccess roleBasedAccess;

    private Evaluation evaluation = new Evaluation();

    public String submitEvaluation() {
        if (roleBasedAccess.isStudent()) {
            evaluationService.submitEvaluation(evaluation);
            return "evaluationSubmitted";
        } else {
            return "accessDenied";
        }
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
}