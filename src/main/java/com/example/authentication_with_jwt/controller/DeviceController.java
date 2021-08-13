package com.example.authentication_with_jwt.controller;


import com.example.authentication_with_jwt.entities.Device;
import com.example.authentication_with_jwt.entities.MyUser;
import com.example.authentication_with_jwt.models.AddDeviceRequest;
import com.example.authentication_with_jwt.models.AddDeviceResponse;
import com.example.authentication_with_jwt.repositories.DeviceRepository;
import com.example.authentication_with_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/devices")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<AddDeviceResponse> AddNewDevice(@RequestBody AddDeviceRequest addDeviceRequest) {
        try {
            Optional<MyUser> user = userRepository.findById(addDeviceRequest.getUserId());
            Device newDevice = new Device(addDeviceRequest.getSeri());
            newDevice.setUser(user.get());
            this.deviceRepository.save(newDevice);
            AddDeviceResponse response = new AddDeviceResponse("Add device success");
            return ResponseEntity.status(200).body(response);

        } catch (Exception err) {
            AddDeviceResponse response = new AddDeviceResponse("Add device failed");
            return ResponseEntity.status(500).body(response);
        }

    }
}
