package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.loan.ReimbursementStatus;

public interface ReimbursementStatusRepo extends JpaRepository<ReimbursementStatus, Long> {
}
