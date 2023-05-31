package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.VSolde;

public interface VSoldeRepo extends JpaRepository<VSolde, Long> {

    VSolde findByClientId(Long clientId);
}
