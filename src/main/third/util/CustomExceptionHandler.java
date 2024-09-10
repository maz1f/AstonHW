package main.third.util;

import main.third.exception.InvalidRequestBody;
import main.third.exception.NotFoundEx;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundEx.class)
    public ResponseEntity<String> handleNotFoundEx(NotFoundEx ex) {
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidRequestBody.class)
    public ResponseEntity<String> handleInvalidRequestBody(InvalidRequestBody ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

}
