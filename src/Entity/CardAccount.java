package Entity;

import java.io.Serializable;

public class CardAccount extends Account implements MonthChangedBalance, Serializable {

    private double monthPayment;

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
        return super.toString() + ", абоненская плата - '" + monthPayment + "'\n";
    }

    @Override
    public void setMonthChangedBalance(Account account) {
        account.setBalance(account.getBalance() - this.monthPayment);
    }
}
