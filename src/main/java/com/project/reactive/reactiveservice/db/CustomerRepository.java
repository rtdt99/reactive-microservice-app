package com.project.reactive.reactiveservice.db;

import com.project.reactive.reactiveservice.model.repo.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {

     Mono<Customer> findByCustomerId(Integer custId);
     Mono<Long> deleteByCustomerId(Integer custId);

}
