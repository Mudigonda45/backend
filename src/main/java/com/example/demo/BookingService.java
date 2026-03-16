//
//
//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class BookingService {
//
//    @Autowired
//    private BookingRepository repository;
//
//    @Autowired
//    private EmailService emailService;
//
//    public void savePayment(MultipartFile file, Long bookingId) throws Exception {
//
//        Booking booking = new Booking();
//
//        booking.setPaymentScreenshot(file.getBytes());
//
//        repository.save(booking);
//
//        emailService.sendPaymentMail(file);
//    }
//
//    public Booking saveBooking(Booking booking) {
//        Booking savedBooking = repository.save(booking);
//
//        try {
//            emailService.sendBookingMail(savedBooking);
//        } catch (Exception e) {
//            // log error but do NOT fail the booking
//            System.err.println("Email sending failed: " + e.getMessage());
//        }
//
//        return savedBooking;
//    }
//
//}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private EmailService emailService;

    // Save new booking and send booking email
    public Booking saveBooking(Booking booking) {
        Booking savedBooking = repository.save(booking);

        try {
            emailService.sendBookingMail(savedBooking);
        } catch (Exception e) {
            System.err.println("Booking email failed: " + e.getMessage());
        }

        return savedBooking;
    }

    // Attach payment screenshot to an existing booking
    public void savePayment(MultipartFile file, Long bookingId) throws Exception {
        Booking booking = repository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));

        booking.setPaymentScreenshot(file.getBytes());
        repository.save(booking);

        try {
            emailService.sendPaymentMail(file);
        } catch (Exception e) {
            System.err.println("Payment email failed: " + e.getMessage());
        }
    }
}