package bz.nimitz.ybr.demo.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class HistorySpecs {

    
    public static Specification<History> isEqualState(State state) {
        return (root, query, builder) -> {
          return builder.equal(root.get("state"), state);
        };
      }  
      
      public static Specification<History> isEqualCreatedAtHistory(LocalDateTime date) {
      return (root, query, builder) -> {
        LocalDateTime date2 = date.plusSeconds(60);
        return builder.between(root.get("createdAt"), date, date2);
      };
    }

    
  }