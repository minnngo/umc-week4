package com.umc.week4.service;

import com.umc.week4.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public void createRental(Map<String, Object> body){
        rentalRepository.save(body);
    }

    public void returnBook(Long rentalId){
        rentalRepository.updateReturnedAt(rentalId);
    }
}
