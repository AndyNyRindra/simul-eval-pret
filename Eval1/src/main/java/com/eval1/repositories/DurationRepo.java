package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.duration.Duration;

public interface DurationRepo extends JpaRepository<Duration, Long> {
}
