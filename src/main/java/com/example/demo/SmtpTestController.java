package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.Socket;

@RestController
public class SmtpTestController {

    @GetMapping("/test-smtp")
    public String testSmtpConnection() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("smtp.gmail.com", 587), 5000);
            return "Connection to smtp.gmail.com:587 succeeded!";
        } catch (Exception e) {
            return "Connection failed: " + e.getMessage();
        }
    }
}