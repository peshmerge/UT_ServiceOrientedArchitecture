package com.utwente.ratefy.QuestionnaireService.repositories;

import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
}
