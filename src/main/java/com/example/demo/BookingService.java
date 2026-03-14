//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
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
//    public Booking saveBooking(Booking booking){
//
//        Booking savedBooking = repository.save(booking);
//
//        // Send email after saving booking
//        emailService.sendBookingMail(savedBooking);
//
//        return savedBooking;
//
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
    private BookingRepository repo;
    @Autowired
   private BookingRepository repository;

    @Autowired
    private EmailService emailService;

    public void savePayment(MultipartFile file) throws Exception {

        Booking booking = new Booking();

        booking.setPaymentScreenshot(file.getBytes());

        repo.save(booking);

        emailService.sendPaymentMail(file);

    }

        public Booking saveBooking(Booking booking){

        Booking savedBooking = repository.save(booking);

        // Send email after saving booking
        emailService.sendBookingMail(savedBooking);

        return savedBooking;

    }
}