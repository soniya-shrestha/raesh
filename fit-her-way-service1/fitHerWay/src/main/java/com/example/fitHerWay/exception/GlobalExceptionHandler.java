package com.example.fitHerWay.exception;



import com.example.fitHerWay.exception.custom.*;
import com.example.fitHerWay.global.BaseController;
import com.example.fitHerWay.global.GlobalErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends BaseController {
    private final ObjectMapper objectMapper;

    private static final String EXCEPTION = "Exception: ";

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handlingGeneralException(GeneralException exception){
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalErrorResponse> handlingEntityNotFoundException(EntityNotFoundException exception){
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GlobalErrorResponse> handleInvalidTokenException (InvalidTokenException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handlingDataIntegrityViolationException(DataIntegrityViolationException exception){
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, "Data integrity violation occurred", exception);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<GlobalErrorResponse> handlingAccessDeniedException(AccessDeniedException exception){
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.FORBIDDEN, "Access denied", exception);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GlobalErrorResponse> handlingIOException(IOException exception){
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Input/output error", exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handlingIllegalArgumentException (IllegalArgumentException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, "Illegal argument", exception);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GlobalErrorResponse> handlingNullPointerException (NullPointerException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Null pointer exception", exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handlingConstraintViolationException (ConstraintViolationException exception) {
        log.error("Constraint violation", exception);
        return errorResponse(HttpStatus.BAD_REQUEST, "Constraint violation", exception);
    }

    @ExceptionHandler(HttpClientErrorException.MethodNotAllowed.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<GlobalErrorResponse> handlingMethodNotAllowedException (HttpClientErrorException.MethodNotAllowed exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.METHOD_NOT_ALLOWED, "Method not allowed", exception);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GlobalErrorResponse> handleUnauthorizedException (HttpClientErrorException.Unauthorized exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", exception);
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleInvalidEmailException (InvalidEmailException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, "Invalid Email", exception);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleInvalidPasswordException (InvalidPasswordException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, "Invalid Email", exception);
    }

    @ExceptionHandler(InvalidPhoneFormat.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleInvalidPhoneFormat (InvalidPhoneFormat exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.error(EXCEPTION, exception);

        // Get the validation message from the annotation
        String validationMessage = Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage();

        return errorResponse(HttpStatus.BAD_REQUEST, validationMessage, exception);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GlobalErrorResponse> handleUsernameNotFoundException (UsernameNotFoundException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GlobalErrorResponse> handleMessagingException (MessagingException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Messaging exception", exception);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<GlobalErrorResponse> handleAuthenticationFailedException (AuthenticationFailedException exception) {
        log.error(EXCEPTION, exception);
        return errorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed", exception);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public void handleExpiredJwtException(ExpiredJwtException e, HttpServletResponse response) throws IOException {
        handleException(response, e, HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorResponse> handleException(Exception e) {
        log.error(EXCEPTION, e);
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(
                LocalDateTime.now(),
                "An unexpected error occurred",
                e.getMessage(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void handleException(HttpServletResponse response, Exception e, int status) throws IOException {
        if (response.isCommitted()) {
            return;
        }
        response.setStatus(status);
        response.setContentType("application/json");
        GlobalErrorResponse exceptionResponse = new GlobalErrorResponse(
                LocalDateTime.now(),
                "JWT token has expired",
                e.getMessage(),
                String.valueOf(status)
        );
        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
        response.getWriter().flush();
        response.getWriter().close();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void handleBadCredentialsException(BadCredentialsException e, HttpServletResponse response) throws IOException {
        handleException(response, e, HttpStatus.UNAUTHORIZED.value());
    }

}

