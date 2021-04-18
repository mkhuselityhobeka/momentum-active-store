package com.active.store.customErrorMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;
@Data
@AllArgsConstructor
@Getter
public class CustomErrorMessage {

    private Date timestamp;
    private HttpStatus httpStatus;
    private String message;
    private String details;
}
