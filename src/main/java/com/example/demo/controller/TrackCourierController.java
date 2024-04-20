package com.example.demo.controller;

import com.example.demo.entity.CourierInfo;
import com.example.demo.repository.CourierRepository;
import com.example.demo.service.CourierService;
import com.example.demo.service.StoresService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class TrackCourierController {
    private final CourierRepository courierRepository;
    private final CourierService courierService;

    public TrackCourierController( CourierRepository courierRepository, CourierService courierService) {
        this.courierRepository = courierRepository;
        this.courierService = courierService;
    }


    @PostMapping("/courierInfo")
    public List<CourierInfo> receiveData(@RequestBody CourierInfo courierInfo) throws IOException {
        courierService.saveCourierLog(courierInfo);
        return courierRepository.findAll();
    }
}
