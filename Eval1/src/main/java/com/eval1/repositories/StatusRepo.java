package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.Status;

public interface StatusRepo extends JpaRepository<Status, Long> {
}
