package com.eval1.models.maxAmount;

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

}