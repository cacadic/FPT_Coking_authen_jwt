package com.example.authentication_with_jwt.services;

import com.example.authentication_with_jwt.entities.Address;
import com.example.authentication_with_jwt.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address getAddress(String ward, String district, String city) {
        return addressRepository.findByWard(ward);
    }
}
