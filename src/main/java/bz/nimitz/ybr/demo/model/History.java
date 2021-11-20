package bz.nimitz.ybr.demo.model;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_id", nullable=false)
    Serv service;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable=false)
    State state;

    private Integer status;

    /*@Enumerated(EnumType.ORDINAL)
    private Availability available;*/

    @Column(name = "created_at")
    /*@CreationTimestamp*/
    private Timestamp createdAt;
   
    public History() {
    }

    public History(Serv service, State state, Integer status, Timestamp createdAt) {
       this.service =service;
       this.state = state;
       this.status = status;
       this.createdAt = createdAt;
    }

    public String toString(){
        
        return this.service.getName() + "-" + this.state.getName() + ":" + this.status;
    }

    public String getService() {
        return service.getName();
    }
    
    public Integer getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }    
    
}