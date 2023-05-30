package com.eval1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import custom.springutils.LoginEntity;
import custom.springutils.exception.CustomException;
import custom.springutils.model.HasId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.String;
import java.lang.Integer;


@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser extends HasId implements LoginEntity {

	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String name;

	@Column(nullable = false)
	private String email;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public Boolean isAdmin() {
		return role.getId().equals(10L);
	}

	public void setPassword(String password) throws CustomException {
		if (password == null || password.equals("")) throw new CustomException("Veuillez ajouter un mot de passe");
		this.password = DigestUtils.sha1Hex(password);
	}

}