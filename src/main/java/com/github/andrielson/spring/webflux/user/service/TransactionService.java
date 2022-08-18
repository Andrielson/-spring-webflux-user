package com.github.andrielson.spring.webflux.user.service;

import com.github.andrielson.spring.webflux.user.dto.TransactionRequestDto;
import com.github.andrielson.spring.webflux.user.dto.TransactionResponseDto;
import com.github.andrielson.spring.webflux.user.dto.TransactionStatus;
import com.github.andrielson.spring.webflux.user.entity.UserTransaction;
import com.github.andrielson.spring.webflux.user.repository.UserRepository;
import com.github.andrielson.spring.webflux.user.repository.UserTransactionRepository;
import com.github.andrielson.spring.webflux.user.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final UserRepository userRepository;

    private final UserTransactionRepository transactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
        return userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(requestDto))
                .flatMap(transactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
    }

    public Flux<UserTransaction> getByUserId(int userId) {
        return transactionRepository.findByUserId(userId);
    }
}
