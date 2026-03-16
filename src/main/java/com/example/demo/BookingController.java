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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService service;

    // Create new booking
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking){
        try {
            Booking saved = service.saveBooking(booking);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    // Upload payment screenshot for a booking
    @PostMapping("/uploadPayment/{bookingId}")
    public ResponseEntity<String> uploadPayment(@PathVariable Long bookingId,
                                                @RequestParam("file") MultipartFile file) {
        try {
            service.savePayment(file, bookingId);
            return ResponseEntity.ok("Payment Uploaded Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload payment");
        }
    }
}