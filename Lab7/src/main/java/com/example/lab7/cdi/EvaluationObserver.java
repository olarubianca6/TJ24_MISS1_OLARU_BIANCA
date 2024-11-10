package com.example.lab7.cdi;

import com.example.lab7.entity.Evaluation;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

@Singleton
public class EvaluationObserver {

    public void onEvaluationSubmitted(@Observes Evaluation evaluation) {
        System.out.println("New evaluation submitted with registration number: " + evaluation.getRegistrationNumber());
    }
}
