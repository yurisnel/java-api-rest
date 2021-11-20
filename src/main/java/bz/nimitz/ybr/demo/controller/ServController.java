package bz.nimitz.ybr.demo.controller;

import bz.nimitz.ybr.demo.model.Serv;
import bz.nimitz.ybr.demo.model.State;
import bz.nimitz.ybr.demo.service.ServService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServController {

    @Autowired
    ServService servService;

    @GetMapping("load")
    public void load() {
        servService.loadData();
    }

    @GetMapping("status")
    public ResponseEntity<?> statusCurrent() {  
        return new ResponseEntity<List<State>>(servService.getStatusCurrent(), HttpStatus.OK);
    }


    @GetMapping("status-current/{state}")
    public ResponseEntity<?> statusCurrentOfState( @PathVariable String state) {
        return new ResponseEntity<List<Serv>>(servService.getAllService(), HttpStatus.OK);
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
