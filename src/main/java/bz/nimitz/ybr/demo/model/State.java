package bz.nimitz.ybr.demo.model;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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
    @OrderBy("createdAt DESC")
    Set<History> history;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<History> getService() {
        LocalDateTime currentTime = LocalDateTime.now();
        return this.getService(currentTime);
    }

    /* Estados vigentes almacenados en la fecha dada */

    public List<History> getService(LocalDateTime date) {

        LocalDateTime previousDate = this.previousUpdateDate(date);

        List<History> serviceStatus = new ArrayList<>();

        serviceStatus.clear();
        for (History h : history) {
            Duration duration = Duration.between(previousDate, h.getCreatedAt());
            long seconds = duration.toSeconds();
            if (seconds < 60) {
                serviceStatus.add(h);
            }
        }
        return serviceStatus;
    }

    public LocalDateTime previousUpdateDate(LocalDateTime date) {
        LocalDateTime temp = null;
        for (History it : history) {
            temp = it.getCreatedAt();
            if (!temp.isAfter(date)) {
                return temp;
            }
        }

        throw new RuntimeException("No update previous this date");
    }

}