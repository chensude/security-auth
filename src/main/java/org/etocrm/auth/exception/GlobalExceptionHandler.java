package org.etocrm.auth.exception;


import lombok.extern.slf4j.Slf4j;
import org.etocrm.database.util.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RefuseException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseVO businessException(RefuseException e) {
        return ResponseVO.error(e.getCode()  , e.getMessage());
    }
}
