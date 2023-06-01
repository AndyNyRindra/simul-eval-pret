package com.eval1.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "v_benefice")
public class VBenefice extends HasId {

	private Double totalempruntes;
	private Double benefice;
	private Double totalrembourses;
	private Integer annee;
	private Integer mois;

}