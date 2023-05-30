package com.eval1.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Date;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "max_duration")
public class MaxDuration extends HasId {

	private BigDecimal duration;
	private Date date;

}