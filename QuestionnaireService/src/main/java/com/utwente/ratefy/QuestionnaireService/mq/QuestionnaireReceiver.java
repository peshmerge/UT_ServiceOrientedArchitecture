package com.utwente.ratefy.QuestionnaireService.mq;

import com.utwente.ratefy.QuestionnaireService.models.Questionnaire;
import com.utwente.ratefy.QuestionnaireService.services.IQuestionnaireService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireReceiver implements RabbitListenerConfigurer {

  @Autowired IQuestionnaireService questionnaireService;

  @Override
  public void configureRabbitListeners(
      RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {}

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void receivedMessage(Questionnaire questionnaire) {
    try {
      questionnaireService.save(questionnaire);
    } catch (Exception e) {
    }
  }
}
