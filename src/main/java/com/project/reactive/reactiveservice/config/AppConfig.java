package com.project.reactive.reactiveservice.config;

import com.project.reactive.reactiveservice.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class AppConfig {
    @Autowired
    CustomerHandler customerHandler;

    @Bean
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions
                .route(GET("/customer").and(accept(MediaType.APPLICATION_JSON)),customerHandler::findAllCustomers)
                .andRoute(GET("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)),customerHandler::findCustomerById)
                .andRoute(POST("/customer").and(accept(MediaType.APPLICATION_JSON)),customerHandler::addCustomer)
                .andRoute(DELETE("/customer/{id}").and(accept(MediaType.APPLICATION_JSON)),customerHandler::deleteCustomer);
    }
}
