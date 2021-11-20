package bz.nimitz.ybr.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ObjectNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> employeeNotFoundHandler(ObjectNotFoundException ex) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("success", "false");
        response.put("message", ex.getMessage());
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
    }
}