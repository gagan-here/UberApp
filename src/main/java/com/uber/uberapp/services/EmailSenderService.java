package com.uber.uberapp.services;

public interface EmailSenderService {
  void sendEmail(String toEmail, String subject, String body);
}
