package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({NotFoundException.class})
    ResponseEntity<ErrorInfo> notFound(HttpServletRequest req, Exception ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMethod(req.getMethod());
        errorInfo.setStatus(status.name());
        errorInfo.setStatusCode(status.value());
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setMsg(ex.getMessage());
        return new ResponseEntity<>(errorInfo, status);
    }

    @ResponseBody
    @ExceptionHandler({UnableToRegisterReservation.class})
    ResponseEntity<ErrorInfo> registrationError(HttpServletRequest req, Exception ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMethod(req.getMethod());
        errorInfo.setStatus(status.name());
        errorInfo.setStatusCode(status.value());
        errorInfo.setTimestamp(LocalDateTime.now());
        errorInfo.setMsg(ex.getMessage());
        return new ResponseEntity<>(errorInfo, status);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ErrorInfo {
    private String method;
    private String status;
    private LocalDateTime timestamp;
    private String msg;
    private Integer statusCode;
}
