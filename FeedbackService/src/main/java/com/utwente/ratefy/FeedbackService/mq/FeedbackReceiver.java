package com.utwente.ratefy.FeedbackService.mq;

import com.utwente.ratefy.FeedbackService.models.Feedback;
import com.utwente.ratefy.FeedbackService.services.IFeedbackService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackReceiver implements RabbitListenerConfigurer {

  @Autowired IFeedbackService feedbackService;

  @Override
  public void configureRabbitListeners(
      RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {}

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void receivedMessage(Feedback feedback) {
    try {
      feedbackService.save(feedback);
    } catch (Exception e) {

    }
  }
}
