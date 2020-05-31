package ru.itis.demo.service;

public interface EmailService {
    void sendMail(String subject, String text, String email);

    void sendTextMail(String subject, String text, String email);
}
