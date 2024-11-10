package com.example.lab7.beans;

import com.example.lab7.repository.EvaluationRepository;
import com.example.lab7.entity.Evaluation;
import com.example.lab7.entity.Teacher;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class ViewEvaluationBean {

    @Inject
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getEvaluationsForTeacher(Teacher teacher) {
        return evaluationRepository.findByTeacher(teacher);
    }
}
