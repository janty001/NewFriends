package com.example.student.newfriends;

/**
 * Created by student on 2/21/15.
 */
public class MyFriends {
    private String name;
    private String email;
    private String phone;
    private int id;

    public MyFriends() {
    }

    public MyFriends(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

    }

    public MyFriends(int id, String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

}
