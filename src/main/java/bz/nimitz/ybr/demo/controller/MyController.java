package bz.nimitz.ybr.demo.controller;

import bz.nimitz.ybr.demo.model.StateResponse;
import bz.nimitz.ybr.demo.service.MyService;
import bz.nimitz.ybr.demo.IStatusCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class MyController {

    @Autowired
    MyService myService;

    /*
     * @GetMapping("load") public void load() { myService.loadDataFromWeb(); }
     */

    /**
     * 3- Devolver por rest el estado actual de los servicios por estado(provincia).
     * 
     * @return
     */
    @GetMapping("status")
    public ResponseEntity<?> statusCurrent() {
        return new ResponseEntity<List<StateResponse>>(myService.getStatusCurrent(), HttpStatus.OK);
    }

    /**
     * 4- Devolver por rest el estado actual del servicio filtrando por estado(provincia)
     * 
     * @param state
     * @return
     */
    @GetMapping("status/{state}")
    public ResponseEntity<?> statusCurrentOfState(@PathVariable String state) {
        return new ResponseEntity<StateResponse>(myService.getStatusCurrent(state), HttpStatus.OK);
    }

    /**
     * 5- Devolver por rest el estado de los servicios por estado(provincia) filtrando por fecha
     * 
     * @param dateTime
     * @return
     */
    @GetMapping("status/date/{dateTime}")
    public ResponseEntity<?> statusFromDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime) {
        return new ResponseEntity<List<StateResponse>>(myService.getStatusByDate(dateTime), HttpStatus.OK);
    }

    /**
     * 6- Retorno por rest qué estado(provincia) tuvo más indisponibilidad de servicio.
     * 
     * @return
     */
    @GetMapping("status/unavailability")
    public ResponseEntity<?> statusFromDate() {
        return new ResponseEntity<IStatusCount>(myService.getStatusMoreDisabled(), HttpStatus.OK);
    }
    

}
