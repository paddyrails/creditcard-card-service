package com.creditcard.card.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CardNotFoundException.class) public ResponseEntity<Map<String,Object>> handleNotFound(CardNotFoundException e){return build(HttpStatus.NOT_FOUND,e.getMessage());}
    @ExceptionHandler(InvalidOperationException.class) public ResponseEntity<Map<String,Object>> handleInvalid(InvalidOperationException e){return build(HttpStatus.BAD_REQUEST,e.getMessage());}
    @ExceptionHandler(Exception.class) public ResponseEntity<Map<String,Object>> handleAll(Exception e){return build(HttpStatus.INTERNAL_SERVER_ERROR,"Error occurred");}
    private ResponseEntity<Map<String,Object>> build(HttpStatus s,String m){Map<String,Object> r=new HashMap<>();r.put("timestamp",LocalDateTime.now());r.put("status",s.value());r.put("message",m);return ResponseEntity.status(s).body(r);}
}
