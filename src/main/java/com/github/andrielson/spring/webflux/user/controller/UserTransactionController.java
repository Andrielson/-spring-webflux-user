package com.github.andrielson.spring.webflux.user.controller;

import com.github.andrielson.spring.webflux.user.dto.TransactionRequestDto;
import com.github.andrielson.spring.webflux.user.dto.TransactionResponseDto;
import com.github.andrielson.spring.webflux.user.entity.UserTransaction;
import com.github.andrielson.spring.webflux.user.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono) {
        return requestDtoMono.flatMap(transactionService::createTransaction);
    }

    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId) {
        return transactionService.getByUserId(userId);
    }

}
