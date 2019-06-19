package com.gilang.retail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FailureResponse {
    private String message;
    private String[] error;
    private Long timeStamp;
}
