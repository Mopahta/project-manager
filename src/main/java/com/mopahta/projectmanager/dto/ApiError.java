package com.mopahta.projectmanager.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

    private final HttpStatus httpStatus;

    private final String message;
}
