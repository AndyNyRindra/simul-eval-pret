package com.eval1.models.loan;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.lang.Double;


@Getter
@Setter
@Entity
@Table(name = "reimbursement")
public class Reimbursement extends HasId {

	private Date date;
	private Double amount;
	private Double total;
	@ManyToOne()
	@JoinColumn(name = "loan_id")
	private Loan loan;
	private Date month;
	private Double rate;
	private Double remains;
	@ManyToOne
	@JoinColumn(name = "status")
	private ReimbursementStatus status;


	public void setRate() {
		setRate(getRemains() * getLoan().getRequest().getRate() / 100);
	}

	public void setTotal() {
		setTotal(getAmount() + getRate());
	}

}