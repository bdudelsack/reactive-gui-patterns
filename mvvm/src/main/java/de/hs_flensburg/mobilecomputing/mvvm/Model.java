package de.hs_flensburg.mobilecomputing.mvvm;

public class Model {
    protected float balance = 0.0f;

    public float getBalance() {
        return balance;
    }

    public void modifyBalance(float value) {
        balance += value;
    }
}