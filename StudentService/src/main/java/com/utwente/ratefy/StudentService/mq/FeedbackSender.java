package com.utwente.ratefy.StudentService.mq;

import com.utwente.ratefy.StudentService.models.Feedback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeedbackSender {
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public FeedbackSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${spring.rabbitmq.exchange}")
  private String exchange;

  @Value("${spring.rabbitmq.routing-key}")
  private String routingKey;

  public void send(Feedback feedback) {
    try {
      rabbitTemplate.convertAndSend(exchange, routingKey, feedback);
    } catch (Exception e) {

    }
  }
}
