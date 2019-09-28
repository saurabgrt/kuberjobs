package com.rarosa.mpandey.kuberjobs.model;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

public class User extends AppCompatActivity {
    private String firstname;
    private String middlename;
    private String lastname;
    private String name;
    private String login_name;
    private Integer uid;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String profile_photo_path;
    private Bitmap profile_photo;
    private String ratings_as_buyer;
    private String ratings_as_supplier;
    private String profession_1_path;
    private String profession_2_path;
    private String profession_3_path;
    private String profession_4_path;
    private String profession_5_path;
    private boolean profession_1_kyc_validated;
    private boolean profession_2_kyc_validated;
    private boolean profession_3_kyc_validated;
    private boolean profession_4_kyc_validated;
    private boolean profession_5_kyc_validated;

    private static Kyc kyc;
    private static UserVarData userVarData;
    private static User user;

    //singleton objects
    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public UserVarData getUserPreference() {
        if (userVarData == null) {
            userVarData = new UserVarData();
        }
        return userVarData;
    }

    public Kyc getKyc() {
        if (kyc == null) {
            kyc = new Kyc();
        }
        return kyc;
    }

    // Accessor methods

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String name) {
        /* remove spaces, leading and trailing */
        this.firstname = name;
    }

    public String getMiddleName() {
        return middlename;
    }

    public void setMiddleName(String name) {
        /* remove spaces, leading and trailing */
        this.middlename = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String name) {
        /* remove spaces, leading and trailing */
        this.lastname = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return login_name;
    }

    public void setLoginName(String name) {
        this.name = login_name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public String getProfilePhotoPath() {
        return address;
    }

    public void setProfilePhotoPath(String path) {
        this.profile_photo_path = path;
    }

    public String getBuyerRating() {
        return ratings_as_buyer;
    }

    public void setBuyerRating(String rating) {
        this.ratings_as_buyer = rating;
    }

    public String getSupplierRating() {
        return ratings_as_supplier;
    }

    public void setSupplierRating(String rating) {
        this.ratings_as_supplier = rating;
    }

    public String getPathProfession1() {
        return profession_1_path;
    }

    public void setPathProfession1(String rating) {
        this.profession_1_path = rating;
    }

    public String getPathProfession2() {
        return profession_1_path;
    }

    public void setPathProfession2(String rating) {
        this.profession_1_path = rating;
    }

    public String getPathProfession3() {
        return profession_1_path;
    }

    public void setPathProfession3(String rating) {
        this.profession_1_path = rating;
    }

    public String getPathProfession4() {
        return profession_1_path;
    }

    public void setPathProfession4(String rating) {
        this.profession_1_path = rating;
    }

    public String getPathProfession5() {
        return profession_1_path;
    }

    public void setPathProfession5(String rating) {
        this.profession_1_path = rating;
    }

    public boolean getValidatedStatusProfession1() {
        return this.profession_1_kyc_validated;
    }

    public void setValidatedStatusProfession1(boolean status) {
        this.profession_1_kyc_validated = status;
    }

    public boolean getValidatedStatusProfession2() {
        return this.profession_2_kyc_validated;
    }

    public void setValidatedStatusProfession2(boolean status) {
        this.profession_2_kyc_validated = status;
    }

    public boolean getValidatedStatusProfession3() {
        return this.profession_1_kyc_validated;
    }

    public void setValidatedStatusProfession3(boolean status) {
        this.profession_1_kyc_validated = status;
    }

    public boolean getValidatedStatusProfession4() {
        return this.profession_2_kyc_validated;
    }

    public void setValidatedStatusProfession4(boolean status) {
        this.profession_2_kyc_validated = status;
    }


    public boolean getValidatedStatusProfession5() {
        return this.profession_1_kyc_validated;
    }

    public void setValidatedStatusProfession5(boolean status) {
        this.profession_1_kyc_validated = status;
    }

    public Bitmap getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(Bitmap profile_photo) {
        this.profile_photo = profile_photo;
    }


}