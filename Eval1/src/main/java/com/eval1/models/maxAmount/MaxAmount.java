package com.eval1.models.maxAmount;

import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;
import java.math.BigDecimal;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "max_amount")
public class MaxAmount extends HasId {

	private Date date;
	private Double amount;

	public void setAmount(Double amount) throws CustomException {
		if (amount <= 0)
			throw new CustomException("Le montant doit être supérieur à 0");
		this.amount = amount;
	}

}