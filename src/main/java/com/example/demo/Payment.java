package com.example.demo;



import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String screenshotPath;

    // constructors
    public Payment() {}

    public Payment(String email, String screenshotPath) {
        this.email = email;
        this.screenshotPath = screenshotPath;
    }

    // getters and setters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getScreenshotPath() { return screenshotPath; }
    public void setScreenshotPath(String screenshotPath) { this.screenshotPath = screenshotPath; }
}