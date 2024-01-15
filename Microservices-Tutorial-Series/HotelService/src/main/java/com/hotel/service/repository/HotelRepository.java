package com.hotel.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<com.hotel.service.entity.Hotel, String> {
}