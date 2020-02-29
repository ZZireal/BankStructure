import Entity.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BankLogic {

    private List<Client> clientList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();

    //инициализация клиентов и счетов для примера
    void initStartUsers() {
        //создание клиентов
        Client cl1 = new PersonClient(1, "физическое лицо", "Генри Кавилл", "1985-05-09");
        Client cl2 = new CompanyClient(2, "юридичесое лицо", "Bartolomeo Inc.", "1996-07-14");
        Client cl3 = new PersonClient(3, "физическое лицо", "Леонардо Ди Каприо", "1976-03-27");
        Client cl4 = new PersonClient(4, "физическое лицо", "Двадцать Пятый Баам", "1976-03-27");

        //создание счетов
        Account acc1 = new DepositAccount(true, 100.56, "AT42552003", "отзывный", 0.8);
        Account acc11 = new DepositAccount(true, -120.67, "BT42555290", "безотзывный", 2.5);
        Account acc2 = new DepositAccount(true, 200.90, "CT42346000", "отзывный", 0.6);
        Account acc3 = new CardAccount(true, 305.15, "UN54663002", 0);
        Account acc4 = new CardAccount(false, 125.15, "BG53468802", 5.5);

        //добавление счетов в лист
        accountList.add(acc1);
        accountList.add(acc11);
        accountList.add(acc2);
        accountList.add(acc3);
        accountList.add(acc4);

        //привязка счетов к клиентам
        cl1.addAccount(acc1);
        cl1.addAccount(acc11);
        cl2.addAccount(acc2);
        cl3.addAccount(acc3);
        cl4.addAccount(acc4);

        //добавление клиентов с привязанными счетами в лист
        clientList.add(cl1);
        clientList.add(cl2);
        clientList.add(cl3);
        clientList.add(cl4);
    }

    //проверка существует ли клиент с данным идентификатором
    boolean ifClientExists(int identifier) {
        //проверка, если клиент существует - остановить метод
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
    boolean ifAccountNumberExists(String number) {
        //проверка, если клиент существует - остановить метод
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

    //создание клиента как юридического
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
    void createCardAccount(int identifier, boolean active, double balance, String number, double monthPayment) {
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
    List<Client> getClients() {
        return clientList;
    }

    //вывести клиентов
    void printClients(List<Client> clientList) {
        for (Client client : clientList) {
            System.out.print(client);
        }
    }

    //вернуть лист счетов
    List<Account> getAccounts() {
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
        List<PersonClient> personClientList = new ArrayList<>();
        List<CompanyClient> companyClientList = new ArrayList<>();

        for (Client client : clientList) {
            if (client instanceof PersonClient) {
                personClientList.add((PersonClient) client);
            }
            if (client instanceof CompanyClient) {
                companyClientList.add((CompanyClient) client);
            }
        }
        for (PersonClient personClient : personClientList) {
            if (identifier == personClient.getIdentifier()) {
                return personClient.getPersonName();
            }
        }
        for (CompanyClient companyClient : companyClientList) {
            if (identifier == companyClient.getIdentifier()) {
                return companyClient.getCompanyTitle();
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
}


