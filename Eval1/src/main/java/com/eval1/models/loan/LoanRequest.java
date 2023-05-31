package com.eval1.models.loan;

import com.eval1.models.appUser.AppUser;
import com.eval1.models.duration.Duration;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.lang.Double;
import java.lang.Integer;
import com.eval1.models.Status;


@Getter
@Setter
@Entity
@Table(name = "loan_request")
public class LoanRequest extends HasId {

	private Date date;
	private Double amount;
	private Double rate;
	@ManyToOne
	@JoinColumn(name = "duration_id")
	private Duration duration;
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private AppUser client;
	@ManyToOne()
	@JoinColumn(name = "status")
	private Status status;

	private Date startReimbursement;

	public void setAmount(Double amount) throws CustomException {
		if (amount <= 0) {
			throw new CustomException("Le montant doit être supérieur à 0");
		}
		this.amount = amount;
	}

	public void setRate(Double rate) throws CustomException {
		if (rate <= 0) {
			throw new CustomException("Le taux doit être supérieur à 0");
		}
		this.rate = rate;
	}
}