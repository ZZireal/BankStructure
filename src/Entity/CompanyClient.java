package Entity;

import Entity.Account;
import Entity.Client;

import java.util.ArrayList;
import java.util.List;

public class CompanyClient extends Client {
    private String CompanyTitle;
    private String dateOfRegistration;

    public CompanyClient(int identifier, String type, String CompanyTitle, String dateOfRegistration) {
        super(identifier, type);
        this.CompanyTitle = CompanyTitle;
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getCompanyTitle() {
        return CompanyTitle;
    }

    public void setName(String CompanyTitle) {
        this.CompanyTitle = CompanyTitle;
    }

    public String getDateOfBirth() {
        return dateOfRegistration;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfRegistration = dateOfBirth;
    }

    @Override
    public String toString() {
        return super.toString() + " " + "Информация о клиенте: " +
                "название компании - '" + CompanyTitle +
                "', дата регистрации - '" + dateOfRegistration +
                "'. Счета клиента: \n" + print() + "\n";
    }

    public String print() {
        String AccountPrinted = "";
        for (Account account : super.getAccountList()) {
            AccountPrinted = account + AccountPrinted;
        }
        return AccountPrinted;
    }
}
