package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.rate.Rate;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface RateRepo extends JpaRepository<Rate, Long> {

    @Query(value = "select * from rate where date <= ?1 order by date desc limit 1", nativeQuery = true)
    Rate findLast(Date date);
}
