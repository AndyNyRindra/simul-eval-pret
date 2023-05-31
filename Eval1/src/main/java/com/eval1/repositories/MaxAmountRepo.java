package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.maxAmount.MaxAmount;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface MaxAmountRepo extends JpaRepository<MaxAmount, Long> {
    @Query(value = "select * from max_amount where date <= ?1 order by date desc limit 1", nativeQuery = true)
    MaxAmount findLast(Date date);
}
