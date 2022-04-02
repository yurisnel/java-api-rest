package bz.nimitz.ybr.demo.controller;

import bz.nimitz.ybr.demo.Utils.IStatusCount;
import bz.nimitz.ybr.demo.model.StateView;
import bz.nimitz.ybr.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController

public class MyController implements MyControllerApi{

    @Autowired
    MyService myService; 
    
    public ResponseEntity<List<StateView>> statusCurrent() {
         List<StateView> result = myService.getStatusCurrent();
        return new ResponseEntity<List<StateView>>(result, HttpStatus.OK);       
        //return ResponseEntity.ok(result);
    }
    
    public ResponseEntity<StateView> statusCurrentOfState(@PathVariable String state) {
        return new ResponseEntity<StateView>(myService.getStatusCurrent(state), HttpStatus.OK);
    }

    public ResponseEntity<List<StateView>> statusFromDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime) {
        return new ResponseEntity<List<StateView>>(myService.getStatusByDate(dateTime), HttpStatus.OK);
    }
    
    public ResponseEntity<IStatusCount> statusFromDate() {
        return new ResponseEntity<IStatusCount>(myService.getStatusMoreDisabled(), HttpStatus.OK);
    }
    

}
