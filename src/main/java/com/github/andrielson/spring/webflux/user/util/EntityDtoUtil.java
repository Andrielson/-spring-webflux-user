package com.github.andrielson.spring.webflux.user.util;

import com.github.andrielson.spring.webflux.user.dto.TransactionRequestDto;
import com.github.andrielson.spring.webflux.user.dto.TransactionResponseDto;
import com.github.andrielson.spring.webflux.user.dto.TransactionStatus;
import com.github.andrielson.spring.webflux.user.dto.UserDto;
import com.github.andrielson.spring.webflux.user.entity.User;
import com.github.andrielson.spring.webflux.user.entity.UserTransaction;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static UserDto toDto(User user) {
        var dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    public static User toEntity(UserDto dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public static UserTransaction toEntity(TransactionRequestDto requestDto) {
        var userTransaction = new UserTransaction();
        userTransaction.setUserId(requestDto.getUserId());
        userTransaction.setAmount(requestDto.getAmount());
        userTransaction.setTransactionDate(LocalDateTime.now());
        return userTransaction;
    }

    public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
        var responseDto = new TransactionResponseDto();
        responseDto.setAmount(requestDto.getAmount());
        responseDto.setUserId(requestDto.getUserId());
        responseDto.setStatus(status);
        return responseDto;
    }
}
