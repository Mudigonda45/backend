package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCarId(Long carId);
    @Query("SELECT b FROM Booking b WHERE b.carId = :carId AND " +
            "(:pickupDate <= b.returnDate AND :returnDate >= b.pickupDate)")
    List<Booking> findOverlappingBookings(@Param("carId") Long carId,
                                          @Param("pickupDate") LocalDate pickupDate,
                                          @Param("returnDate") LocalDate returnDate);
}