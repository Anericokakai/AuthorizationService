package com.auth.authorizationserver.Respository;

import com.auth.authorizationserver.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles,Integer> {


    Optional<Roles> findByName(String  name);
}
