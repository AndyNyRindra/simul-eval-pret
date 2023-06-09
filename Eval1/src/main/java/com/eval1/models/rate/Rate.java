package com.eval1.models.rate;

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
@Table(name = "rate")
public class Rate extends HasId {

	private Date date;
	private Double rate;

	public void setRate(Double amount) throws CustomException {
		if (amount <= 0)
			throw new CustomException("Le taux doit être supérieur à 0");
		this.rate = amount;
	}

}