package com.example.authentication_with_jwt.models;

import com.example.authentication_with_jwt.entities.Address;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    private String username;
    private String password;
    private String ward;
    private String district;
    private String city;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, String password, String ward, String district, String city) {
        this.username = username;
        this.password = password;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
