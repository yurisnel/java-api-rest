package bz.nimitz.ybr.demo.model;
import java.util.List;


public class StateResponse{

    String state;
    List<History> services;
   
    public StateResponse(String state, List<History> services) {
        this.state = state;
        this.services = services;
    }

    public String getState() {
        return state;
    }   

    public List<History> getServices() {
        return services;
    }

}