package com.bet.sportoto.user.repository;

import com.bet.sportoto.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByMail(String mail);
    boolean existsByMail(String mail);
}
