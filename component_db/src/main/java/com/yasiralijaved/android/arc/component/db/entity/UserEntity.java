package com.yasiralijaved.android.arc.component.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UserEntity.TABLE_NAME)
public class UserEntity {

    public static final String TABLE_NAME = "user_table";
    public static final String COLUMN_USER_ID = "user_id";

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_USER_ID)
    private String id;

    private String website;

    private String phone;

    private String name;

    private String email;

    private String username;

    private String photoUrl;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public UserEntity(String id, String website, String phone, String name, String email, String username, String photoUrl) {
        this.id = id;
        this.website = website;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.username = username;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "UserEntity [website = " + website + ", phone = " + phone + ", name = " + name + ", id = " + id + ", email = " + email + ", username = " + username + ", photoUrl = " + photoUrl + "]";
    }
}
