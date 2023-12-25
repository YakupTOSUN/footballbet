package com.bet.sportoto.userbet.entity;



import com.bet.sportoto.userbetround.entity.UserBetRound;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "UserBet")
public class UserBet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private Selection selection;
    private boolean isGuesCorrect;


    public UserBet create(Selection selection){
        setSelection(selection);
        return this;
    }
}
