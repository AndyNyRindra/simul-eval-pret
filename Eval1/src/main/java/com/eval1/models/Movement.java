package com.eval1.models;

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


@Getter
@Setter
@Entity
@Table(name = "movement")
public class Movement extends HasId {

	private Date date;
	private Double amount;
	private Integer clientId;
	private Integer moveTypeId;

}