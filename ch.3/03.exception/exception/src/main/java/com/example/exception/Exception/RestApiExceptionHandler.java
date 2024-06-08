package com.example.exception.Exception;

import com.example.exception.Controller.RestApiController;
import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(basePackageClasses = {RestApiController.class})
@Order(1)
public class RestApiExceptionHandler {

    //    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity exception(
//            Exception e
//    ){
//        log.error("RestApiExceptionHandler", e);
//
//        return ResponseEntity.status(200).build();
//    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Api> exception(
            Exception e
    ) {
        log.error("", e);
        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity outOfBound(
            IndexOutOfBoundsException e
    ) {
        log.error("IndexOutOfBoundsException", e);

        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(
            NoSuchElementException e
    ) {
        log.error("NoSuchElement", e);

        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND))
                .resultMessage(HttpStatus.NOT_FOUND.name())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
