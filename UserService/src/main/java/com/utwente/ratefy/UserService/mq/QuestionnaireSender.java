package com.utwente.ratefy.UserService.mq;

import com.utwente.ratefy.UserService.models.Questionnaire;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireSender {
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public QuestionnaireSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${spring.rabbitmq.exchange}")
  private String exchange;

  @Value("${spring.rabbitmq.routing-key}")
  private String routingKey;

  public void send(Questionnaire questionnaire) {
    try {
      rabbitTemplate.convertAndSend(exchange, routingKey, questionnaire);
    } catch (Exception e) {

    }
  }
}
