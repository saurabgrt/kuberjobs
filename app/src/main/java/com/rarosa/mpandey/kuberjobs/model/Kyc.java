package com.rarosa.mpandey.kuberjobs.model;

import android.support.v7.app.AppCompatActivity;

public class Kyc extends AppCompatActivity {
    private String uid;
    private String social_security_name;
    private String social_security_number;
    private String upi_id;
    private boolean social_security_verified;

    // Accessor methods

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getSocialSecurityName() {
        return social_security_name;
    }

    public void setSocialSecurityName(String name) {
        this.social_security_name = name;
    }

    public String getSocialSecurityNumber() {
        return social_security_number;
    }

    public void setSocialSecurityNumber(String name) {
        this.social_security_number = name;
    }

    public String getUpiId() {
        return upi_id;
    }

    public void setUpiId(String upi) {
        this.upi_id = upi;
    }

    public boolean getSocialSecurityVerifyStatus() {
        return social_security_verified;
    }

    public void setSocialSecurityVerifyStatus(boolean status) {
        this.social_security_verified = status;
    }
}