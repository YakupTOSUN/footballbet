package com.bet.sportoto.mail;

public interface MailService {
    String sendMailPassword(String password, String mail);

    void couponResult(String mail, String result);
}
