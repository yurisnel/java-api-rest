package bz.nimitz.ybr.demo.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Serv {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "service")
    Set<History> states;

    public Serv() {
    }

    public Serv(String name) {      
        this.name = name;
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

}