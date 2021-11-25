package bz.nimitz.ybr.demo.controller;

import bz.nimitz.ybr.demo.Utils.IStatusCount;
import bz.nimitz.ybr.demo.model.StateView;
import bz.nimitz.ybr.demo.service.MyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "Status services", description = "API para la gestión de la disponibilidad de servicios por estado (provincia)")

public class MyController {

    @Autowired
    MyService myService;
  

    /**
     * 3- Devolver por rest el estado actual de los servicios por estado(provincia).
     * 
     * @return
     */
    @GetMapping("/services/status")
    public ResponseEntity<?> statusCurrent() {
        return new ResponseEntity<List<StateView>>(myService.getStatusCurrent(), HttpStatus.OK);
    }

    /**
     * 4- Devolver por rest el estado actual del servicio filtrando por estado(provincia)
     * 
     * @param state
     * @return
     */
    @GetMapping("/services/status/{state}")
    public ResponseEntity<?> statusCurrentOfState(@PathVariable String state) {
        return new ResponseEntity<StateView>(myService.getStatusCurrent(state), HttpStatus.OK);
    }

    /**
     * 5- Devolver por rest el estado de los servicios por estado(provincia) filtrando por fecha
     * 
     * @param dateTime
     * @return
     */
    @GetMapping("/services/status/date/{dateTime}")
    public ResponseEntity<?> statusFromDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime) {
        return new ResponseEntity<List<StateView>>(myService.getStatusByDate(dateTime), HttpStatus.OK);
    }

    /**
     * 6- Retorno por rest qué estado(provincia) tuvo más indisponibilidad de servicio.
     * 
     * @return
     */
    @GetMapping("/services/most_unavailable")
    public ResponseEntity<?> statusFromDate() {
        return new ResponseEntity<IStatusCount>(myService.getStatusMoreDisabled(), HttpStatus.OK);
    }
    

}
