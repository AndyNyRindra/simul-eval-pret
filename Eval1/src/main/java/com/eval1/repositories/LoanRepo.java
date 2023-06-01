package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.loan.Loan;

public interface LoanRepo extends JpaRepository<Loan, Long> {
}
