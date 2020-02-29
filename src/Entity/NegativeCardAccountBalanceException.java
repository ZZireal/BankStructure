package Entity;

public class NegativeCardAccountBalanceException extends Exception {

    private double balanceException;

    public NegativeCardAccountBalanceException(double balance) {
        balanceException = balance;
    }

    public String toString() {
        return "Баланс не должен быть отрицательным (" + balanceException + ").";
    }
}