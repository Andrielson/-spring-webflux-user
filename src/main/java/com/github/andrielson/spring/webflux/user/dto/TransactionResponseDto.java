package com.github.andrielson.spring.webflux.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {
    private Integer userId;
    private Integer amount;
    private TransactionStatus status;
}
