package com.utwente.ratefy.FeedbackService.repositories;

import com.utwente.ratefy.FeedbackService.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
