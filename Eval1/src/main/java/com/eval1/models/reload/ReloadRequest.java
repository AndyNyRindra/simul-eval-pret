package com.eval1.models.reload;

import com.eval1.models.appUser.AppUser;
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
import com.eval1.models.Status;


@Getter
@Setter
@Entity
@Table(name = "reload_request")
public class ReloadRequest extends HasId {

	private Date date;
	private Double amount;
	@ManyToOne()
	@JoinColumn(name = "client_id")
	private AppUser client;
	@ManyToOne()
	@JoinColumn(name = "status")
	private Status status;

	public void setAmount(Double amount) throws CustomException {
		if (amount <= 0)
			throw new CustomException("Le montant doit être supérieur à 0");
		this.amount = amount;
	}
}