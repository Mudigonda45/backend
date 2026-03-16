//package com.example.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/booking")
//@CrossOrigin("*")
//public class BookingController {
//
//    @Autowired
//    private BookingService service;
//
//    @PostMapping("/create")
//    public Booking createBooking(@RequestBody Booking booking){
//        return service.saveBooking(booking);
//    }
//    @PostMapping("/uploadPayment")
//    public String uploadPayment(@RequestParam("file") MultipartFile file) throws Exception {
//
//        service.savePayment(file);
//
//        return "Payment Uploaded Successfully";
//    }
//
//}


package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService service;

    // Create a new booking
    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking){
        return service.saveBooking(booking);
    }

    // Upload payment screenshot for an existing booking
    @PostMapping("/uploadPayment")
    public String uploadPayment(@RequestParam("file") MultipartFile file,
                                @RequestParam("bookingId") Long bookingId) throws Exception {

        service.savePayment(file, bookingId);

        return "Payment Uploaded Successfully";
    }
}