package com.project.eldalell.eldalell_delivery;

public class Address {
    private String id , address_subject,street,
            buliding_number,floor,apartment_number
            ,district_id,user_id,city,district;

    public Address(String id, String address_subject, String street, String buliding_number, String floor, String apartment_number, String district_id, String user_id) {
        this.id = id;
        this.address_subject = address_subject;
        this.street = street;
        this.buliding_number = buliding_number;
        this.floor = floor;
        this.apartment_number = apartment_number;
        this.district_id = district_id;
        this.user_id = user_id;
    }

    public Address() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress_subject() {
        return address_subject;
    }

    public void setAddress_subject(String address_subject) {
        this.address_subject = address_subject;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuliding_number() {
        return buliding_number;
    }

    public void setBuliding_number(String buliding_number) {
        this.buliding_number = buliding_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
