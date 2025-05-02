package com.example.tastehaven_application;

public class Reservation {
    private String userName;
    private String reservationDate;
    private String reservationTime;
    private int numberOfGuests;

    public Reservation() {
        // Default constructor required for Firebase
    }

    public Reservation(String userName, String reservationDate, String reservationTime, int numberOfGuests) {
        this.userName = userName;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
