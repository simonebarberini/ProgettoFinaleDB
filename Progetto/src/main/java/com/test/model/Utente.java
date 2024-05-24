package com.test.model;

public class Utente {
    private int userID;
    private String username;
    private String password;
    private String email;
    private double balance;

    public Utente(int userId, String username, String password, String email, double balance) {
        this.userID = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    public int getUserid() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void setUserid(int userid) {
        this.userID = userid;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email + '\'' +
                ", balance=" + balance + '\'' + 
                '}';
    }
}
