package bz.nimitz.ybr.demo.repository;

import bz.nimitz.ybr.demo.model.State;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findByName(String name);
}
