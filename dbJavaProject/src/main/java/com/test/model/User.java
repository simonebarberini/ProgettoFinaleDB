package com.test.model;

import java.util.Scanner;

import com.test.dbService.UserDao;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private double balance;

    public User(int id, String username, String password, String email, double balance){
        this.id =id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void versamento(){
        System.out.println("Quanto vuoi versare?");
        Scanner in = new Scanner(System.in);
        double soldi= in.nextDouble();
        this.balance+=soldi;
        UserDao dao= new UserDao();
        dao.setBalance(this);
        in.close();
    }

    
}