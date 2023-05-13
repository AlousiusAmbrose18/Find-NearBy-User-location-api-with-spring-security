package com.alousi.userlocation.service;

import com.alousi.userlocation.entity.UserLocation;
import com.alousi.userlocation.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserLocationServiceImpl implements UserLocationService{

    @Autowired
    private UserLocationRepository userLocationRepository;
    @Override
    public UserLocation createData(UserLocation userLocation) {
        return userLocationRepository.save(userLocation);
    }

    @Override
    public UserLocation updateData(Long id, UserLocation userLocation) {
        Optional<UserLocation> optionalUserLocation = userLocationRepository.findById(id);
        if (optionalUserLocation.isPresent()) {
            UserLocation existingUserLocation = optionalUserLocation.get();
            existingUserLocation.setName(userLocation.getName());
            existingUserLocation.setLatitude(userLocation.getLatitude());
            existingUserLocation.setLongitude(userLocation.getLongitude());
            return userLocationRepository.save(existingUserLocation);
        } else
            return null;
    }

    @Override
    public List<UserLocation> getNearByUserLocations(Integer number) {
        List<UserLocation> allUserLocations = userLocationRepository.findAll();
        // calculate distances and sort by distance
        return allUserLocations.stream()
                .sorted(Comparator.comparingDouble(u -> calculateDistance(u.getLatitude(), u.getLongitude())))
                .limit(number)
                .collect(Collectors.toList());
    }

    private Double calculateDistance(Double latitude, Double longitude) {
        return Math.sqrt(latitude * latitude + longitude * longitude);
    }
}
