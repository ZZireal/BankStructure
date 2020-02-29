package Entity;

import Entity.*;

abstract public class Account {
    private boolean active;
    private double balance;
    private String number;

    Account(boolean active, double balance, String number) {
        this.active = active;
        this.balance = balance;
        this.number = number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "    Статус счета - '" + active +
                "', баланс счета - '" + balance +
                "', номер счета - '" + number + "'";
    }
}
