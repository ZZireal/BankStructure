package Entity;

import Entity.Account;
import Entity.Client;

import java.util.ArrayList;
import java.util.List;

public class PersonClient extends Client {
    private String PersonName;
    private String dateOfBirth;
    private List<Account> accountList;

    public PersonClient(int identifier, String type, String PersonName, String dateOfBirth) {
        super(identifier, type);
        this.PersonName = PersonName;
        this.dateOfBirth = dateOfBirth;
        this.accountList = new ArrayList<>();
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String PersonName) {
        this.PersonName = PersonName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    @Override
    public String toString() {
        return super.toString() + " " + "Информация о клиенте: " +
                "имя - '" + PersonName +
                "', дата рождения - '" + dateOfBirth +
                "'. Счета клиента: \n" + print() + "\n";
    }


    public String print() {
        String bankAccountPrinted = "";
        for (Account account : accountList) {
            bankAccountPrinted = account + bankAccountPrinted;
        }
        return bankAccountPrinted;
    }
}