package com.driver.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phnoeNumber;
    private String password;
    private int a;

    public User(){

    }

    public User(String name, String phnoeNumber, String password, List<Reservation> reservationList) {
        this.name = name;
        this.phnoeNumber = phnoeNumber;
        this.password = password;
        this.reservationList = reservationList;
    }

    public User(String name, String phnoeNumber, String password) {
        this.name = name;
        this.phnoeNumber = phnoeNumber;
        this.password = password;
    }

    @OneToMany(mappedBy ="user" ,cascade = CascadeType.ALL)
    List<Reservation> reservationList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnoeNumber() {
        return phnoeNumber;
    }

    public void setPhnoeNumber(String phnoeNumber) {
        this.phnoeNumber = phnoeNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
