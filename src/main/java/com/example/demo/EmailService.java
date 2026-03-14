package com.example.demo;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingMail(Booking booking){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mohansaimudigonda@gmail.com");
        message.setTo("mohansaimudigonda@gmail.com");

        message.setSubject("New Car Booking");

        message.setText(
                "New Booking Received\n\n" +
                        "Name: " + booking.getName() +
                        "\nPhone: " + booking.getPhone() +
                        "\nEmail: " + booking.getEmail() +
                        "\nCar ID: " + booking.getCarId() +
                        "\nPickup Date: " + booking.getPickupDate() +
                        "\nReturn Date: " + booking.getReturnDate()
        );

        mailSender.send(message);

    }

    public void sendPaymentMail(MultipartFile file) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message,true);

        helper.setTo("yourmail@gmail.com");
        helper.setSubject("New Payment Received");
        helper.setText("User uploaded payment screenshot");

        helper.addAttachment(
                file.getOriginalFilename(),
                file
        );

        mailSender.send(message);
    }
}