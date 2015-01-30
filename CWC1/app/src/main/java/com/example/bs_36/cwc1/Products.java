package com.example.bs_36.cwc1;

import java.security.PrivateKey;

/**
 * Created by BS-36 on 1/28/2015.
 */
public class Products {
    private String phone;
    private String email;
    private String address;
    private String longitude;
    private String latitude;
    private String district;
    private String mobile;
    private String branch_name;
    private String imgUrl;

    public Products(String imgUrl,String phone, String email, String address, String longitude, String latitude, String district, String mobile, String branch_name) {
        super();
        this.imgUrl = imgUrl;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.district = district;
        this.mobile = mobile;
        this.branch_name = branch_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}
