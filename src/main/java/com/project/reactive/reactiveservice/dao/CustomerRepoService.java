package com.project.reactive.reactiveservice.dao;

import com.project.reactive.reactiveservice.db.CustomerRepository;
import com.project.reactive.reactiveservice.model.repo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerRepoService {
    @Autowired
    CustomerRepository customerRepo;

    public Flux<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }

    public Mono<Customer> findCustomerById(Integer id){
        if(id == null){
            return Mono.empty();
        }

        return customerRepo.findByCustomerId(id);
    }

    public Mono<Customer> addCustomer(Mono<Customer> customerMono){
        return customerMono
                .flatMap(customer -> customerRepo.save(customer))
                .doOnError(ex-> System.out.println("Failed to add document :"+ex.getMessage()))
                .doOnSuccess(customer -> System.out.println("Successfully added customer record id :"+customer.getId()));
    }

    public Mono<Long> deleteCustomer(Integer id){
        if(id == null){
            return Mono.empty();
        }
        return customerRepo.deleteByCustomerId(id);
    }
}
