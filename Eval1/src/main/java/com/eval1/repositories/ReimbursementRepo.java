package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.loan.Reimbursement;

public interface ReimbursementRepo extends JpaRepository<Reimbursement, Long> {
}
