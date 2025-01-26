package com.uber.uberapp;

import com.uber.uberapp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

  @Autowired private EmailSenderService emailSenderService;

  @Test
  void contextLoads() {
    emailSenderService.sendEmail(
        "chatwidmeah@gmail.com", "This is the testing email", "Body of my email");
  }

  @Test
  void sendEmailMultiple() {
    String[] emails = {"chatwidmeah@gmail.com", "kathet.tulasha@gmail.com"};

    emailSenderService.sendEmail(emails, "Hello from Uber Application demo", "Keep coding!!");
  }
}
