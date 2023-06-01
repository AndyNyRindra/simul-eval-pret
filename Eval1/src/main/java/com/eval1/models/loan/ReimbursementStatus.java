package com.eval1.models.loan;

import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.lang.String;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "reimbursement_status")
public class ReimbursementStatus extends HasId {

	private String name;
	private String color;

}