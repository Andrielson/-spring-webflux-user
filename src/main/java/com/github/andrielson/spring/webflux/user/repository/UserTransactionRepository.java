package com.github.andrielson.spring.webflux.user.repository;

import com.github.andrielson.spring.webflux.user.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {
    Flux<UserTransaction> findByUserId(int userId);
}
