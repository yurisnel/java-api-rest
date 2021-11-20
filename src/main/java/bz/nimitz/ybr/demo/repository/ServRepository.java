package bz.nimitz.ybr.demo.repository;

import bz.nimitz.ybr.demo.model.Serv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServRepository extends JpaRepository<Serv, Long> {
    List<Serv> findByName(String name);
}
