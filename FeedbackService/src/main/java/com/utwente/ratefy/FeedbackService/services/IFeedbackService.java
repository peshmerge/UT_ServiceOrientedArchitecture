package com.utwente.ratefy.FeedbackService.services;

import com.utwente.ratefy.FeedbackService.models.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {

    List<Feedback> findAll();

    Optional<Feedback> findById(Integer id);

    Feedback save(Feedback feedback);

    void deleteById(Integer id);
}
