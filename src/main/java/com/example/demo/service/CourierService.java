package com.example.demo.service;

import com.example.demo.entity.CourierInfo;
import com.example.demo.model.StoreInfo;
import com.example.demo.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;


@Service
public class CourierService {
    private final CourierRepository courierRepository;
    private final StoresService storesService;

    public CourierService(CourierRepository courierRepository, StoresService storesService) {
        this.courierRepository = courierRepository;
        this.storesService = storesService;
    }

    // Function to calculate the distance between a courier and a store
    public double calculateDistanceToStore(CourierInfo courierInfo, StoreInfo storeInfo) {
        double R = 6371000.0; // Radius of the Earth in meters
        double latDistance = Math.toRadians(storeInfo.getLat() - courierInfo.getLat());
        double lonDistance = Math.toRadians(storeInfo.getLng() - courierInfo.getLng());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(courierInfo.getLat())) * Math.cos(Math.toRadians(storeInfo.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }

    // Function to save courier log when entering a store
    public void saveCourierLog(CourierInfo courierInfo) throws IOException {
        storesService.getStores().stream().forEach(store->{
           if (calculateDistanceToStore(courierInfo,store)<100){
               courierInfo.setStoreName(store.getName());
               if(isCourierEnterenceSameStoreLessThanOneMin(courierInfo)) {
                   courierRepository.save(courierInfo);
               }
           }
        });

    }
    // Function to check if the time difference between two instances is less than a minute
    public  boolean isDifferenceGreaterThanOneMinute(Instant instant1, Instant instant2) {
        Duration duration = Duration.between(instant1, instant2);
        return duration.abs().getSeconds() < 60;
    }
    // Function to check if the courier entered the same store less than a minute ago
    public boolean isCourierEnterenceSameStoreLessThanOneMin(CourierInfo courierInfo){
        return courierRepository.findAll().stream()
                .filter(x->x.getStoreName()!=null)
                .filter(x->x.getStoreName().equals(courierInfo.getStoreName()))
                .noneMatch(x->isDifferenceGreaterThanOneMinute(x.getTime(),courierInfo.getTime()));
    }
}
