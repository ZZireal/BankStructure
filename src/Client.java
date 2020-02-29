import java.util.ArrayList;
import java.util.List;

abstract public class Client {
    private int identifier;
    private String type;
    private List<Account> accountList;

    Client(int identifier, String type) {
        this.identifier = identifier;
        this.type = type;
        this.accountList = new ArrayList<>();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    void addAccount(Account account) {
        this.accountList.add(account);
    }

    public List getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        return "ID клиента - '" + identifier +
                "', тип - '" + type + "'.";
    }
}
