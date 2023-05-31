package com.eval1.models.duration;

import com.eval1.models.HasDeleted;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "duration")
public class Duration extends HasDeleted {

	private Double duration;

	public void setDuration(Double amount) throws CustomException {
		if (amount <= 0)
			throw new CustomException("La durée doit être supérieur à 0");
		this.duration = amount;
	}

}