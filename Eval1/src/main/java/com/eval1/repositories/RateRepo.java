package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.Rate;

public interface RateRepo extends JpaRepository<Rate, Long> {
}
