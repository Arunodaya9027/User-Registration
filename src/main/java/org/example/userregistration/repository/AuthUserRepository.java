package org.example.userregistration.repository;

import org.example.userregistration.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {
}
