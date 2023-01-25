package com.example.abc_bangk;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private double balance;
    private String type;

    public Account(String name, String title, int balance, String type) {
        this.title = title;

        this.balance = balance;
        this.type = type;
    }

    public Account() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Accout{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + balance +
                ", type='" + type + '\'' +
                "}\n";
    }
}
