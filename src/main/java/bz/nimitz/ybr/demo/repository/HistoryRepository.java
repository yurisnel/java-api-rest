package bz.nimitz.ybr.demo.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bz.nimitz.ybr.demo.model.History;
import bz.nimitz.ybr.demo.model.State;

public interface HistoryRepository extends JpaRepository<History, Long>, JpaSpecificationExecutor<History> {
    //List<History> findTop8ByOrderByCreatedAtDesc();
    History findFirstByOrderByCreatedAtDesc();
    List<History> findByStateAndCreatedAt(State state, LocalDateTime dateTime);
}


