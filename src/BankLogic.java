import Entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BankLogic {

    public BankLogic(List<Client> clientList, List<Account> accountList) {
        this.clientList = clientList;
        this.accountList = accountList;
    }

    public BankLogic() {
    }

    private List<Client> clientList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    //инициализация клиентов и счетов для примера
    void initStartUsers() throws IOException, ClassNotFoundException, NegativeCardAccountBalanceException {
        //создание клиентов
        Client cl1 = new PersonClient(1, "физическое лицо", "Генри Кавилл", "1985-05-09");
        Client cl2 = new CompanyClient(2, "юридическое лицо", "Bartolomeo Inc.", "1996-07-14");
        Client cl3 = new PersonClient(3, "физическое лицо", "Леонардо Ди Каприо", "1976-03-27");
        Client cl4 = new PersonClient(4, "физическое лицо", "Двадцать Пятый Баам", "1976-03-27");

        //создание счетов
        Account acc1 = new DepositAccount(true, 100.56, "DA11", "отзывный", 0.8);
        Account acc11 = new DepositAccount(true, -120.67, "DA12", "безотзывный", 2.5);
        Account acc2 = new DepositAccount(true, 200.90, "DA21", "отзывный", 0.6);
        Account acc22 = new CreditAccount(true, 1000, "CR1", "краткосрочный", 10);
        Account acc3 = new CardAccount(true, 305.15, "CA31", 0);

        //пример исключения
        try {
            Account acc4 = new CardAccount(false, -125.15, "CA41", 5.5);
            accountList.add(acc4);
            cl4.addAccount(acc4);
            if (acc4.getBalance() < 0) throw new NegativeCardAccountBalanceException(acc4.getBalance());
        } catch (NegativeCardAccountBalanceException ex) {
            System.out.println(ex.toString());
        }

        Account acc44 = new CreditAccount(true, 1500, "CR42", "долгосрочный", 20);

        //добавление счетов в лист
        accountList.add(acc1);
        accountList.add(acc11);
        accountList.add(acc2);
        accountList.add(acc22);
        accountList.add(acc3);
        accountList.add(acc44);

        //привязка счетов к клиентам
        cl1.addAccount(acc1);
        cl1.addAccount(acc11);
        cl2.addAccount(acc2);
        cl2.addAccount(acc22);
        cl3.addAccount(acc3);
        cl4.addAccount(acc44);

        //добавление клиентов в лист
        clientList.add(cl1);
        clientList.add(cl2);
        clientList.add(cl3);
        clientList.add(cl4);
    }

    //добавить новый лист в accountList
    void addInAccountList(List<Account> accountListNew) {
        accountList.addAll(accountListNew);
    }

    //проверка существует ли клиент с данным идентификатором
    boolean isClientExists(int identifier) {
        boolean exist = false;
        for (Client client : clientList) {
            if (identifier == client.getIdentifier()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    //проверка существует ли счет с данным номером
    boolean isAccountNumberExists(String number) {
        boolean exist = false;
        for (Account account : accountList) {
            if (number.equals(account.getNumber())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    //создание клиента как физического лица
    void createPersonClient(int identifier, String type, String name, String dateOfBirth) {
        // scanner in main, принимает объекты (везде, где сканнер так седлать)
        Client client = new PersonClient(identifier, type, name, dateOfBirth);
        clientList.add(client);
    }

    //создание клиента как юридического лица
    void createCompanyClient(int identifier, String type, String name, String dateOfRegistration) {
        // scanner in main, принимает объекты (везде, где сканнер так седлать)
        Client client = new CompanyClient(identifier, type, name, dateOfRegistration);
        clientList.add(client);
    }

    //создание депозитного счета
    void createDepositAccount(int identifier, boolean active, double balance, String number, String type, double percent) {
        for (Client client : clientList) {
            if (identifier == client.getIdentifier()) {
                Account acc = new DepositAccount(active, balance, number, type, percent);
                client.addAccount(acc);
                accountList.add(acc);
                return;
            }
        }
    }

    //создание карточного счета
    void createCardAccount(int identifier, boolean active, double balance, String number, double monthPayment) throws NegativeCardAccountBalanceException {
        for (Client client : clientList) {
            if (identifier == client.getIdentifier()) {
                Account acc = new CardAccount(active, balance, number, monthPayment);
                client.addAccount(acc);
                accountList.add(acc);
                return;
            }
        }
    }

    //создание кредитного счета
    void createCreditAccount(int identifier, boolean active, double balance, String number, String type, double percent) {
        for (Client client : clientList) {
            if (identifier == client.getIdentifier()) {
                Account acc = new CreditAccount(active, balance, number, type, percent);
                client.addAccount(acc);
                accountList.add(acc);
                return;
            }
        }
    }

    //вернуть лист клиентов
    List<Client> getClientList() {
        return clientList;
    }

    //вывести клиентов
    void printClients(List<Client> clientList) {
        for (Client client : clientList) {
            System.out.print(client);
        }
    }

    //вернуть лист счетов
    List<Account> getAccountList() {
        return accountList;
    }

    //вывести счета
    void printAccounts(List<Account> accountList) {
        for (Account account : accountList) {
            System.out.print(account);
        }
    }

    void setActiveAccount(String number, boolean status) {
        for (Account account : accountList) {
            if (number.equals(account.getNumber())) {
                account.setActive(status);
            }
        }
    }

    //вернуть имя (название) клиента
    String getNameOrTitleById(int identifier) {
        for (Client client : clientList) {
            if (client instanceof PersonClient && identifier == client.getIdentifier()) {
                return ((PersonClient) client).getPersonName();
            }
            if (client instanceof CompanyClient && identifier == client.getIdentifier())  {
                return ((CompanyClient) client).getCompanyTitle();
            }
        }
        return null;
    }

    //отсортировать лист счетов
    List<Account> getSortedAccounts() {
        Comparator<Account> c = new Comparator<>() {
            @Override
            public int compare(Account bankAccount1, Account bankAccount2) {
                if (bankAccount1.getBalance() > bankAccount2.getBalance())
                    return 1;
                if (bankAccount1.getBalance() < bankAccount2.getBalance())
                    return -1;
                if (bankAccount1.getBalance() == bankAccount2.getBalance())
                    return 0;
                return 0;
            }
        };
        accountList.sort(c);
        return accountList;
    }

    //вернуть сумму всех счетов
    double getSumAllAccounts() {
        double sum = 0;
        for (Account ba : accountList) {
            sum += ba.getBalance();
        }
        return sum;
    }

    //вернуть сумму положительных счетов
    double getSumPositiveAccounts() {
        double sumPositive = 0;
        for (Account ba : accountList) {
            if (ba.getBalance() > 0) sumPositive += ba.getBalance();
        }
        return sumPositive;
    }

    //вернуть сумму отрицательных счетов
    double getSumNegativeAccounts() {
        double sumNegative = 0;
        for (Account ba : accountList) {
            if (ba.getBalance() < 0) sumNegative += ba.getBalance();
        }
        return sumNegative;
    }

    //вернуть лист счетов клиента по имени (названию)
    List<Account> getAccountsOfPerson(String name) {
        for (Client client : clientList) {
            if (name.equals(getNameOrTitleById(client.getIdentifier()))) {
                return client.getAccountList();
            }
        }
        return null;
    }

    //установить новый баланс спустя месяц
    void setMonthChangedBalance() {
        for (Account account : accountList) {
            if (account instanceof CardAccount) {
                ((CardAccount) account).setMonthChangedBalance(account);
            }
            if (account instanceof DepositAccount) {
                ((DepositAccount) account).setMonthChangedBalance(account);
            }
            if (account instanceof CreditAccount) {
                ((CreditAccount) account).setMonthChangedBalance(account);
            }
        }
    }
}