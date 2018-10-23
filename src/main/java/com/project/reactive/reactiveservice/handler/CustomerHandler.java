package com.project.reactive.reactiveservice.handler;

import com.project.reactive.reactiveservice.dao.CustomerRepoService;
import com.project.reactive.reactiveservice.model.repo.Customer;
import com.project.reactive.reactiveservice.model.request.CustomerRequest;
import com.project.reactive.reactiveservice.model.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomerHandler {

    @Autowired
    CustomerRepoService customerService;

    public Mono<ServerResponse> findAllCustomers(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customerService.findAllCustomers(), Customer.class);
    }

    public Mono<ServerResponse> findCustomerById(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("id"))
                .map(x -> Integer.valueOf(x))
                .onErrorReturn(0)
                .flatMap(id->{
                    if(id == 0){
                        return ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(new CustomerResponse("", "400", "Bad/Malformed request", "400", getDateNow())), CustomerResponse.class);
                    }else{
                        return customerService.findCustomerById(id)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(Mono.just(result),Customer.class))
                                .switchIfEmpty(ServerResponse.notFound().build());
                    }
                });
    }


    public Mono<ServerResponse> addCustomer(ServerRequest serverRequest){
        Mono<CustomerRequest> customerRequestMono = serverRequest.bodyToMono(CustomerRequest.class);
        Mono<Customer> customerMono = customerRequestMono.flatMap(customer ->{
            Customer c = new Customer();
            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            c.setGender(customer.gender);
            c.setCity(customer.getCity());
            c.setCustomerId(customer.getCustId());
            return Mono.just(c);
        });
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customerService.addCustomer(customerMono),Customer.class);
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest serverRequest){
        return Mono.just(serverRequest.pathVariable("id"))
                .map(id->Integer.valueOf(id))
                .onErrorReturn(0)
                .flatMap(i->{
                    if(i == 0){
                        return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body(Mono.just(new CustomerResponse("DELETE FAILED", "400", "INVALID ID ", "400", getDateNow())),CustomerResponse.class);
                    }else {
                        return customerService.deleteCustomer(i)
                                .onErrorReturn(-1L)
                                .flatMap(idLong->{
                                    if(idLong<0L){
                                        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN).body(Mono.just("FAILED: Delete Record"),String.class);
                                    }else if(idLong == 0L){
                                        return ServerResponse.notFound().build();
                                    }
                                    else{
                                        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("SUCCESS: Customer Record Deleted"),String.class);
                                    }
                                });
                    }})
                .log();
    }

    public String getDateNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return  sdf.format(new Date());
    }
}
