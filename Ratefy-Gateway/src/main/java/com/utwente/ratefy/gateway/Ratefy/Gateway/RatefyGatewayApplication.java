package com.utwente.ratefy.gateway.Ratefy.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class RatefyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatefyGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("feedback-service", r -> r
                        .path("/api/v1/feedbacks/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9091/v1/feedbacks/"))

                .route("questionnaire-service", r -> r
                        .path("/api/v1/questionnaires/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9092/v1/questionnaires/"))

                .route("reward-service", r -> r
                        .path("/api/v1/rewards/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9093/v1/rewards/"))

                .route("user-service", r -> r
                        .path("/api/v1/users/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9094/v1/users/"))

                .route("student-service", r -> r
                        .path("/api/v1/students/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:9095/v1/students/"))

                .build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
