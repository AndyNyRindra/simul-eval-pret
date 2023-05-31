package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.reload.ReloadRequest;

public interface ReloadRequestRepo extends JpaRepository<ReloadRequest, Long> {
}
