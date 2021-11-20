package bz.nimitz.ybr.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bz.nimitz.ybr.demo.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findTop8ByOrderByCreatedAtDesc();
}
