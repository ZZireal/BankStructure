package Entity;

import Entity.Account;

public class DepositAccount extends Account implements MonthChangedBalance {
    private double percent;
    private String type;

    public DepositAccount(boolean active, double balance, String number, String type, double percent) {
        super(active, balance, number);
        this.percent = percent;
        this.type = type;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + ", тип - " + type + "', процентная ставка - '" + percent + "'\n";
    }

    @Override
    public void setMonthChangedBalance(Account account) {
            account.setBalance(account.getBalance() + account.getBalance() * this.percent * 0.01);
    }
}
