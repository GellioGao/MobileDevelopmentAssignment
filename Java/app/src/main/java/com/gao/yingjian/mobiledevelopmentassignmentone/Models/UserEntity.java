package com.gao.yingjian.mobiledevelopmentassignmentone.Models;

public class UserEntity {
    private int userID;
    private String username;
    private String firstName;
    private String lastName;

    public UserEntity(int userID, String username, String firstName, String lastName) {
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
