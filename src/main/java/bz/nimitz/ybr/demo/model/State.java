package bz.nimitz.ybr.demo.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "state")
    Set<History> history;

    @Column
    @ElementCollection(targetClass=Integer.class)
    Set<History> serviceStatus;

    public State() {       
    }

    public State(String name) {
        this.name = name;
        this.filterHistory(new Timestamp(System.currentTimeMillis()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<History> getService() {
        return serviceStatus;
    }

    public void filterHistory(Timestamp date) {
       
        Timestamp previousDate = this.previousDate(date);

        serviceStatus.clear();
        for (History h : history) {
            int seconds = (int) (h.getCreatedAt().getTime() - previousDate.getTime()) / 1000;
            if (seconds < 60) {
                serviceStatus.add(h);
            }
        }
    }
    
    public Timestamp previousDate(Timestamp date) {
        Long temp = (long) 0;        
        Long param = date.getTime();
        for (History it : history) {
            temp = it.getCreatedAt().getTime();
            if (temp <= param) {
              return it.getCreatedAt();
            }
        }
        return date;
    }

}