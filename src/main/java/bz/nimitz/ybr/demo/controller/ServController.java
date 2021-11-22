package bz.nimitz.ybr.demo.controller;

import bz.nimitz.ybr.demo.model.StateResponse;
import bz.nimitz.ybr.demo.service.ServService;
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
public class ServController {

    @Autowired
    ServService servService;

    @GetMapping("load")
    public void load() {
        servService.loadData();
    }

    /**
     * 3- Devolver por rest el estado actual de los servicios por estado(provincia).
     * 
     * @return
     */
    @GetMapping("status")
    public ResponseEntity<?> statusCurrent() {
        return new ResponseEntity<List<StateResponse>>(servService.getStatusCurrent(), HttpStatus.OK);
    }

    /**
     * 4- Devolver por rest el estado actual del servicio filtrando por estado(provincia)
     * 
     * @param state
     * @return
     */
    @GetMapping("status/{state}")
    public ResponseEntity<?> statusCurrentOfState(@PathVariable String state) {
        return new ResponseEntity<StateResponse>(servService.getStatusCurrent(state), HttpStatus.OK);
    }

    /**
     * 5- Devolver por rest el estado de los servicios por estado(provincia) filtrando por fecha
     * @param dateTime
     * @return
     */
    @GetMapping("status/date/{dateTime}")
    public ResponseEntity<?> statusFromDate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateTime) {
        return new ResponseEntity<List<StateResponse>>(servService.getStatusByDate(dateTime), HttpStatus.OK);
    }

    /**
     * 6- Retorno por rest qué estado(provincia) tuvo más indisponibilidad de servicio.
     * @return
     */
    @GetMapping("status/unavailability")
    public ResponseEntity<?> statusFromDate() {
        return new ResponseEntity<IStatusCount>(servService.getStatusMoreDisabled(), HttpStatus.OK);
    }

    /*
     * @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id) {
     * 
     * Serv serv = servService.getService(id); return new ResponseEntity<Serv>(serv,
     * HttpStatus.OK); }
     * 
     * @PostMapping("") ResponseEntity<?> add(@RequestBody Serv newServ) { Serv serv
     * = servService.saveService(newServ); return new ResponseEntity<Serv>(serv,
     * HttpStatus.OK); }
     * 
     * @PutMapping("/{id}") public ResponseEntity<?> update(@RequestBody Serv
     * serv, @PathVariable Long id) { Serv existServ = servService.getService(id);
     * existServ.setName(serv.getName()); servService.saveService(serv); return new
     * ResponseEntity<>(HttpStatus.OK);
     * 
     * }
     * 
     * @DeleteMapping("/{id}") boolean delete(@PathVariable Long id) { return
     * servService.deleteService(id); }
     */
}
