package com.umc.week4.controller;

import com.umc.week4.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    // POST http://localhost:8080/rentals
    @PostMapping
    public String createRental(@RequestBody Map<String, Object> body){
        rentalService.createRental(body);
        return "도서 대여가 완료되었습니다!";
    }

    // PATCH http://localhost:8080/rentals/{rentalId}/return
    @PatchMapping("/{rentalId}/return")
    public String returnBook(@PathVariable Long rentalId){
        rentalService.returnBook(rentalId);
        return "도서 반납이 완료되었습니다!";
    }
}
