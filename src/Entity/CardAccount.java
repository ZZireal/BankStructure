package Entity;

import Entity.Account;

public class CardAccount extends Account {
    double monthPayment;

    public CardAccount(boolean active, double balance, String number, double monthPayment) {
        super(active, balance, number);
        this.monthPayment = monthPayment;
    }

    public double getMonthPayment() {
        return monthPayment;
    }

    public void setMonthPayment(double monthPayment) {
        this.monthPayment = monthPayment;
    }

    @Override
    public String toString() {
        return super.toString() + ", абоненская плата - '" + "'\n";
    }
}
