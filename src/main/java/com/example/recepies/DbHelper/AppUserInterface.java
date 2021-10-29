package com.example.recepies.DbHelper;

import com.example.recepies.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository("AppUsersRepository")
public interface AppUserInterface extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findAppUserByUsername(String username);
}
