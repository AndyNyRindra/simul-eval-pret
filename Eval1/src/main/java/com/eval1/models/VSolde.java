package com.eval1.models;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.Double;
import java.lang.Long;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "v_solde")
public class VSolde extends HasId {

	private Double solde;
	private Long id;
	private Integer clientId;

}