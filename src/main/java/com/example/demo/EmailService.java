//package com.example.demo;
//
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendBookingMail(Booking booking){
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("mohansaimudigonda@gmail.com");
//        message.setTo("mohansaimudigonda@gmail.com");
//
//        message.setSubject("New Car Booking");
//
//        message.setText(
//                "New Booking Received\n\n" +
//                        "Name: " + booking.getName() +
//                        "\nPhone: " + booking.getPhone() +
//                        "\nEmail: " + booking.getEmail() +
//                        "\nCar ID: " + booking.getCarId() +
//                        "\nPickup Date: " + booking.getPickupDate() +
//                        "\nReturn Date: " + booking.getReturnDate()
//        );
//
//        mailSender.send(message);
//
//    }
//
//    public void sendPaymentMail(MultipartFile file) throws Exception {
//
//        MimeMessage message = mailSender.createMimeMessage();
//
//        MimeMessageHelper helper =
//                new MimeMessageHelper(message,true);
//
//        helper.setTo("yourmail@gmail.com");
//        helper.setSubject("New Payment Received");
//        helper.setText("User uploaded payment screenshot");
//
//        helper.addAttachment(
//                file.getOriginalFilename(),
//                file
//        );
//
//        mailSender.send(message);
//    }
//}


package com.example.demo;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.Attachment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class EmailService {

    @Value("${resend.api.key}")
    private String apiKey;

    public void sendBookingMail(Booking booking) throws Exception {

        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to("quotexmohan45@gmail.com")
                .subject("New Car Booking")
                .html(
                        "<h3>New Booking Received</h3>" +
                                "<p>Name: " + booking.getName() + "</p>" +
                                "<p>Phone: " + booking.getPhone() + "</p>" +
                                "<p>Email: " + booking.getEmail() + "</p>" +
                                "<p>Car ID: " + booking.getCarId() + "</p>" +
                                "<p>Pickup Date: " + booking.getPickupDate() + "</p>" +
                                "<p>Return Date: " + booking.getReturnDate() + "</p>"
                )
                .build();

        resend.emails().send(params);
    }

    public void sendPaymentMail(MultipartFile file) throws Exception {

        Resend resend = new Resend(apiKey);

        String encodedFile = Base64.getEncoder().encodeToString(file.getBytes());

        Attachment attachment = Attachment.builder()
                .fileName(file.getOriginalFilename())
                .content(encodedFile)
                .build();

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to("mohansaimudigonda@gmail.com")
                .subject("New Payment Received")
                .html("<p>User uploaded payment screenshot</p>")
                .attachments(new Attachment[]{attachment})
                .build();

        resend.emails().send(params);
    }
}