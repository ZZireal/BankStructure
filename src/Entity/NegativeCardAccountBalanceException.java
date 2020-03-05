package Entity;

public class NegativeCardAccountBalanceException extends Exception {

    private double balanceException;

    public NegativeCardAccountBalanceException(double balance) {
        this.balanceException = balance;
    }

    public String toString() {
        return "Пользовательское исключение! Баланс не должен быть отрицательным (" + balanceException + " < 0).";
    }
}