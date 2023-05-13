package com.alousi.userlocation.service;

import com.alousi.userlocation.entity.UserLocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserLocationService {

    public UserLocation createData(UserLocation userLocation);

    public UserLocation updateData(Long id, UserLocation userLocation);

    public List<UserLocation> getNearByUserLocations(Integer number);
}
