package com.example.validationexample.advice;

import com.example.validationexample.controller.ApiController;
import com.example.validationexample.dto.ErrorDetail;
import com.example.validationexample.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestControllerAdvice(basePackageClasses = ApiController.class)
public class ApiControllerAdvice {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> processConstraintViolationException(ConstraintViolationException e,
                                                                      HttpServletRequest request) {
        List<ErrorDetail> errorList = new ArrayList<>();
        e.getConstraintViolations().forEach(violation -> {
            Stream<Path.Node> stream = StreamSupport.stream(violation.getPropertyPath().spliterator(), false);
            List<Path.Node> nodeList = stream.collect(Collectors.toList());
            String argumentName = nodeList.get(nodeList.size() - 1).getName();
            Object invalidValue = violation.getInvalidValue();
            ErrorDetail errorMessage = new ErrorDetail(argumentName,
                    violation.getMessage(),
                    invalidValue == null ? "" : invalidValue.toString());

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                request.getRequestURI(),
                "잘못된 형식의 인자값이 존재합니다",
                "FAIL",
                errorList);

        System.out.printf("exception occurred. exception class: %s, message: %s\n", e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                         HttpServletRequest request) {
        List<ErrorDetail> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            Object value = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();

            ErrorDetail errorMessage = new ErrorDetail(fieldName, message, value.toString());
            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                request.getRequestURI(),
                "잘못된 형식의 인자값이 존재합니다",
                "FAIL",
                errorList);

        System.out.printf("exception occurred. exception class: %s, message: %s\n", e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> processException(Exception e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                request.getRequestURI(),
                "서버 내부의 에러가 발생하였습니다",
                "FAIL");

        System.out.printf("exception occurred. exception class: %s, message: %s\n", e.getClass().getName(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
