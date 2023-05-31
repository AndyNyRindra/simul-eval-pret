package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.maxAmount.MaxAmount;

public interface MaxAmountRepo extends JpaRepository<MaxAmount, Long> {
}
