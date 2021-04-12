package com.utwente.ratefy.gateway.Ratefy.Gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RatefyGatewayApplication {

    @Value("${feedback-service-url}")
    private String feedbackService;

    @Value("${questionnaire-service-url}")
    private String questionnaireService;


    public static void main(String[] args) {
        SpringApplication.run(RatefyGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("feedback-service", r -> r
                        .path("/api/v1/feedbacks/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(feedbackService))

                .route("questionnaire-service", r -> r
                        .path("/api/v1/questionnaires/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri(questionnaireService))

                .route("reward-service", r -> r
                        .path("/api/v1/rewards/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9093/v1/rewards/"))

                .route("schedule-service", r -> r
                        .path("/api/v1/schedules/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9094/v1/schedules/"))

                .route("user-service", r -> r
                        .path("/api/v1/users/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9095/v1/users/"))

                .route("student-service", r -> r
                        .path("/api/v1/students/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9096/v1/students/"))

                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
