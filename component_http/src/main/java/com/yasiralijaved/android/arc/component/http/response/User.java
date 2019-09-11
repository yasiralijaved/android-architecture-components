package com.yasiralijaved.android.arc.component.http.response;

import com.yasiralijaved.android.arc.component.db.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String website;

    private Address address;

    private String phone;

    private String name;

    private Company company;

    private int id;

    private String email;

    private String username;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserEntity [website = " + website + ", address = " + address + ", phone = " + phone + ", name = " + name + ", company = " + company + ", id = " + id + ", email = " + email + ", username = " + username + "]";
    }

    public static UserEntity mapHttpResponse(User response) {

        return new UserEntity(response.getId(),
                response.getWebsite(),
                response.getPhone(),
                response.getName(),
                response.getEmail(),
                response.getUsername(),
                "");
    }

    public static List<UserEntity> mapHttpResponse(List<User> response) {

        List<UserEntity> userList = new ArrayList<>();
        for(User user : response) {
            userList.add(new UserEntity(user.getId(),
                    user.getWebsite(),
                    user.getPhone(),
                    user.getName(),
                    user.getEmail(),
                    user.getUsername(),
                    ""));
        }
        return userList;
    }

}
