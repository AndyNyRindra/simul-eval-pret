package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.MaxDuration;

public interface MaxDurationRepo extends JpaRepository<MaxDuration, Long> {
}
