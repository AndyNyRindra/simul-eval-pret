package com.eval1.models.duration;

import com.eval1.models.HasDeleted;
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

	private BigDecimal duration;

}