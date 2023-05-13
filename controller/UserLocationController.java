package com.alousi.userlocation.controller;

import com.alousi.userlocation.entity.UserLocation;
import com.alousi.userlocation.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class UserLocationController {

    @Autowired
    private UserLocationService userLocationService;

    @PostMapping("/create")
    @PreAuthorize("ADMIN")
    public ResponseEntity<?> createUserLocation(@RequestBody UserLocation userLocation) {
        UserLocation createdUserLocation = userLocationService.createData(userLocation);
        return ResponseEntity.ok(createdUserLocation);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("ADMIN")
    public ResponseEntity<?> updateUserLocation(@PathVariable("id") Long id, @RequestBody UserLocation userLocation) {
        UserLocation userLocationUpdated = userLocationService.updateData(id, userLocation);
        if (userLocationUpdated != null)
            return ResponseEntity.ok(userLocationUpdated);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nearby/{number}")
    @PreAuthorize("READER")
    public ResponseEntity<?> getNearbyUserLocations(@PathVariable("number") Integer number) {
        List<UserLocation> userLocations = userLocationService.getNearByUserLocations(number);
        if(userLocations !=null && !userLocations.isEmpty())
            return ResponseEntity.ok(userLocations);
        return ResponseEntity.noContent().build();
    }

}