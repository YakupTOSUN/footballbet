package com.bet.sportoto.betround.repository;

import com.bet.sportoto.betround.entity.BetRound;
import com.bet.sportoto.betround.entity.BetStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRoundRepository extends JpaRepository<BetRound, String> {

    Page<BetRound> findByBetStatus(BetStatus betStatus, Pageable allPageable);
}
