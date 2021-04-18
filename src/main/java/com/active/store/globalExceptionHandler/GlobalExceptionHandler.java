package com.active.store.globalExceptionHandler;

import com.active.store.customErrorMessage.CustomErrorMessage;
import com.active.store.exceptions.CustomerNotFoundException;
import com.active.store.exceptions.InSufficientActivePointsException;
import com.active.store.exceptions.InternalServerException;
import com.active.store.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> productNotFoundException(ProductNotFoundException productNotFoundException, WebRequest request, HttpStatus httpStatus){
        CustomErrorMessage errorMessage = new CustomErrorMessage(new Date (), httpStatus.NOT_FOUND, productNotFoundException.getMessage(),request.getDescription (false));
        return new ResponseEntity<> (errorMessage, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?>customerNotFoundException(CustomerNotFoundException customerNotFoundException, WebRequest request){
        CustomErrorMessage errorMessage = new CustomErrorMessage(new Date(), HttpStatus.NOT_FOUND, customerNotFoundException.getMessage(),request.getDescription (false));
        return new ResponseEntity<> (errorMessage, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InSufficientActivePointsException.class)
    public ResponseEntity<?>inSuffientActivePointsException(InSufficientActivePointsException inSufficientActivePointsException, WebRequest request,HttpStatus httpStatus){
        CustomErrorMessage errorMessage = new CustomErrorMessage(new Date(), httpStatus.NOT_FOUND, inSufficientActivePointsException.getMessage(),request.getDescription (false));
        return new ResponseEntity<> (errorMessage,HttpStatus.FORBIDDEN);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity internalServerException(InternalServerException internalServerException,WebRequest request,HttpStatus httpStatus){
        CustomErrorMessage errorMessage = new CustomErrorMessage(new Date(), httpStatus.NOT_FOUND, internalServerException.getMessage(),request.getDescription (false));

        return new ResponseEntity (errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
