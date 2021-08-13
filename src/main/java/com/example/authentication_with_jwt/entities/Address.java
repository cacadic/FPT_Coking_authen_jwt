package com.example.authentication_with_jwt.entities;

import javax.persistence.*;
import java.util.Set;

@Entity()
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column()
    private String ward;

    @Column()
    private String district;

    @Column()
    private String city;

    @ManyToMany(mappedBy = "address")
    private Set<MyUser> user;

    public Address(String ward, String district, String city) {
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<MyUser> getUser() {
        return user;
    }

    public void setUser(Set<MyUser> user) {
        this.user = user;
    }
}
