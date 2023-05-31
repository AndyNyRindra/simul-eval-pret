package com.eval1.repositories;

import custom.springutils.LoginRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eval1.models.appUser.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long>, LoginRepo<AppUser> {
}
