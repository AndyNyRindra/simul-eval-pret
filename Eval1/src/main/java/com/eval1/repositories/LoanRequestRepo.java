package com.eval1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.loan.LoanRequest;

public interface LoanRequestRepo extends JpaRepository<LoanRequest, Long> {
}
