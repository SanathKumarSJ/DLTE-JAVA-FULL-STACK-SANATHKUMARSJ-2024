package org.example;

import java.io.Serializable;

public class User extends org.database.User implements Serializable {
    private String username;
    private String password;
    private String address;
    private String email;
    private Long contact;
    private Double balance;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact=" + contact +
                ", balance=" + balance +
                '}';
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Long getContact() {
        return contact;
    }

    @Override
    public void setContact(Long contact) {
        this.contact = contact;
    }

    @Override
    public Double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User(String username, String password, String address, String email, Long contact, Double balance, String username1, Double balance1) {
        super(username, password, address, email, contact, balance);
        this.username = username1;
        this.balance = balance1;
    }

    public User(String username, Double balance) {
        this.username = username;
        this.balance = balance;
    }
}
