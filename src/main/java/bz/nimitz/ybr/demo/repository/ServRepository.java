package bz.nimitz.ybr.demo.repository;

import bz.nimitz.ybr.demo.IStatusCount;
import bz.nimitz.ybr.demo.model.Serv;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServRepository extends JpaRepository<Serv, Long> {
    
    List<Serv> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT s.name AS state, COUNT(*) AS total "
    + "FROM history AS h INNER JOIN states s ON s.id = h.state_id WHERE h.status = :mStatus GROUP BY h.state_id, h.status ORDER BY total DESC")
    List<IStatusCount> countTotalStatusInterface(@Param("mStatus") String mStatus);
}

