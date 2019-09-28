package com.rarosa.mpandey.kuberjobs.model;

import android.graphics.Bitmap;

public class UserVarData {
    private String uid;
    private String user_choice;
    private String profession_search;
    private String task_short_description;
    private String task_detail_description;
    private Bitmap task_photo;

    // Accessor methods

    public String getUid() {
        return uid;
    }

    public void setUid(String name) {
        this.uid = name;
    }

    public String getUserChoice() {
        return user_choice;
    }

    public void setUserChoice(String choice) {
        this.user_choice = choice;
    }

    public String getProfessionSearch() {
        return profession_search;
    }

    public void setProfessionSearch(String profession) {
        this.profession_search = profession;
    }

    public String getTaskShortDescription() {
        return task_short_description;
    }

    public void setTaskShortDescription(String description) {
        this.task_short_description = description;
    }

    public String getTaskDetailDescription() {
        return task_detail_description;
    }

    public void setTaskDetailDescription(String description) {
        this.task_detail_description = description;
    }

    public Bitmap getTask_photo() {
        return task_photo;
    }

    public void setTask_photo(Bitmap task_photo) {
        this.task_photo = task_photo;
    }

}