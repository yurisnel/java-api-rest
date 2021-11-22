package bz.nimitz.ybr.demo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;


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

    //private Integer status;

    @Column(columnDefinition = "ENUM('PENDING', 'ACTIVE', 'INACTIVE', 'NULL')")
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /*@CreationTimestamp*/
    private LocalDateTime createdAt;
   
    public History() {
    }

    public History(Serv service, State state, EStatus status, LocalDateTime createdAt) {
       this.service = service;
       this.state = state;
       this.status = status;
       this.createdAt = createdAt;
    }

    public String toString(){
        
        return this.service.getName() + "-" + this.state.getName() + ":" + this.status;
    }

    public String getName() {
        return service.getName();
    }
    
    public EStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }    
    
    
}