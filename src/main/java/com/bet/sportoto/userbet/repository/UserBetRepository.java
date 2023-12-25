package com.bet.sportoto.userbet.repository;

import com.bet.sportoto.userbet.entity.UserBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBetRepository extends JpaRepository<UserBet, String> {
}
