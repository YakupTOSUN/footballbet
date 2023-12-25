package com.bet.sportoto.userbetround.repository;

import com.bet.sportoto.userbetround.entity.UserBetRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBetRoundRepository extends JpaRepository<UserBetRound, String> {
}
