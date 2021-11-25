package bz.nimitz.ybr.demo.model;
import java.util.List;


public class StateView{

    Long id;    
    String name;
    List<History> services;
   
    public StateView(Long id, String name, List<History> services) {
        this.id = id;
        this.name = name;
        this.services = services;
    }
 
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }   

    public List<History> getServices() {
        return services;
    }

}