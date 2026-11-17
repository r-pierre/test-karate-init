package com.arc.gpts.tech.bdddemo.infra.config;

import com.arc.gpts.tech.bdddemo.exceptions.BadRequestException;
import com.arc.gpts.tech.bdddemo.exceptions.BusinessException;
import com.arc.gpts.tech.bdddemo.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex) {
        log.error("Bad request: ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        log.error("Bad request: ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedException(Exception ex) {
        log.error("Unexpected error: ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An error has raised.");
    }

}
