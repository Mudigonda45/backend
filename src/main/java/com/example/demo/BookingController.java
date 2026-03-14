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

    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking booking){
        return service.saveBooking(booking);
    }
    @PostMapping("/uploadPayment")
    public String uploadPayment(@RequestParam("file") MultipartFile file) throws Exception {

        service.savePayment(file);

        return "Payment Uploaded Successfully";
    }

}