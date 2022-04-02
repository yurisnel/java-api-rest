package bz.nimitz.ybr.demo.Utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {
    
    @ResponseBody 
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> NotFoundHandler(RecordNotFoundException ex) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("success", "false");
        response.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
    }
}