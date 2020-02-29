import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BankLogic {

    private List<Client> clientList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();

    void initStartUsers() {
        //создание клиентов
        Client cl1 = new PersonClient(1, "Генри Кавилл", "физическое лицо", "1985-05-09");
        Client cl2 = new CompanyClient(2, "Bartolomeo Inc.", "юрюдическое лицо", "1996-07-14");
        Client cl3 = new PersonClient(3, "Леонардо Ди Каприо", "физическое лицо", "1976-03-27");
        Client cl4 = new PersonClient(4, "Двадцать Пятый Баам", "физическое лицо", "1976-03-27");

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

    public boolean ifClientExists(int identifier) {
        //проверка, если клиент существует - остановить метод
        boolean exist = false;
        for (Client client : clientList) {
            if (identifier == client.getIdentifier()) {
                exist = true;
                return exist;
            }
        }
        return false;
    }

    public void createPersonClient(int identifier, String name, String type, String dateOfBirth) {
        try {
            // scanner in main, принимает объекты (везде, где сканнер так седлать)
            Client client = new PersonClient(identifier, name, type, dateOfBirth);
            clientList.add(client);
            System.out.print("Клиент успешно создан!");
        } catch (Exception ex) {
            System.out.println("Введены некорретные данные!");
        }
    }

    public void createCompanyClient(int identifier, String name, String type, String dateOfRegistration) {
        try {
            // scanner in main, принимает объекты (везде, где сканнер так седлать)
            Client client = new CompanyClient(identifier, name, type, dateOfRegistration);
            clientList.add(client);
            System.out.print("Клиент успешно создан!");
        } catch (Exception ex) {
            System.out.println("Введены некорретные данные!");
        }
    }

    public void createDepositAccount() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nВыберите ID клиента: ");
            for (Client client : clientList) {
                System.out.println(client);
            }
            int identifier = Integer.parseInt(scanner.nextLine());
            for (Client client : clientList) {
                if (identifier == client.getIdentifier()) {
                    System.out.print("\nВведите true или false, чтобы счет был активным или заблокированным: ");
                    boolean active = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Введите сумму счета: ");
                    double balance = Double.parseDouble(scanner.nextLine());
                    System.out.print("Введите номер счета: ");
                    String number = scanner.nextLine();
                    System.out.print("Введите тип счета: ");
                    String type = scanner.nextLine();
                    System.out.print("Введите процентную ставку: ");
                    int percent = Integer.parseInt(scanner.nextLine());
                    DepositAccount acc = new DepositAccount(active, balance, number, type, percent);
                    client.addAccount(acc);
                    accountList.add(acc);
                    System.out.print("\n" +
                            "Счет успешно создан!\n" +
                            "ID клиента - '" + client.getIdentifier() + ":\n" + acc);
                    return;
                }
            }
            System.out.print("Клиент не существует!");
        } catch (Exception ex) {
            System.out.println("Введены некорретные данные!");
        }
    }

    public void getClients() {
        //сделать ретурн, это логика, не надо вывод
        for (Client client : clientList) {
            System.out.print(client);
        }
    }

    public void getAllBankAccounts() {
        for (Account account : accountList) {
            System.out.print(account);
        }
    }

    /*public void setActiveAccount() {
        try {
            Scanner scanner = new Scanner(System.in);
            Client client1 = null;

            System.out.print("Выберите ID клиента: \n");
            for (Client client : clientList) {
                System.out.print(client);
            }

            int identifier = scanner.nextInt();
            for (Client client : clientList) {
                if (identifier == client.getIdentifier()) {
                    client1 = client;
                }
            }

            if (client1 != null) {
                int i = 1;
                for (Object account : client1.getBankAccountArrayList()) {
                    System.out.print(i + ") " + account);
                    i++;
                }
            }

            Scanner scannerInt = new Scanner(System.in);
            System.out.print("\nВыберите счет: ");
            int number = scannerInt.nextInt();
            System.out.print("Введите статус: ");
            Scanner scannerBoolean = new Scanner(System.in);
            boolean status = scannerBoolean.nextBoolean();
            assert client1 != null;
            client1.getBankAccountArrayList().get(number - 1).setActive(status);
            System.out.print("Установлен статус " + status + " для счета!");
        } catch (Exception ex) {
            System.out.println("Введены некорретные данные!");
        }
    }
*/
    /*
    static public void printBankAccountsPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите идентификатор клиента, счета которого необходимо вывести: ");
        int identifier = scanner.nextInt();
        int i = 1;
        for (BankClient bc : bankClientsList) {
            if (identifier == bc.getIdentifier()) {
                for (BankAccount ba : bc.getBankAccountArrayList()) {
                    System.out.println(i + ") " + ba);
                    i++;
                }
            }
        }
    }
    */

    public void getAllSortedAccounts() {
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
        getAllBankAccounts();
    }

    public void getSumAllAccounts() {
        //тоже не вывод а ретурн

        double sum = 0;
        for (Account ba : accountList) {
            sum += ba.getBalance();
        }
        System.out.print("Сумма всех счетов: " + sum);
    }

    public void getSumPositiveNegativeAccounts() {
        double sumPositive = 0;
        double sumNegative = 0;
        for (Account ba : accountList) {
            if (ba.getBalance() >= 0) sumPositive += ba.getBalance();
            else sumNegative += ba.getBalance();
        }
        System.out.println("Сумма положительных счетов: " + sumPositive);
        System.out.print("Сумма отрицательных счетов: " + sumNegative);
    }

    /*public void searchAccountOfPerson() {
        Scanner scanner = new Scanner(System.in);
//тоже ретурн
        System.out.print("Введите ФИО клиента, счета которого необходимо вывести: ");
        String name = scanner.nextLine();
        int i = 1;
        boolean exist = false;
        for (Client client : clientList) {
            if (name.equals(client.getPersonName())) {
                exist = true;
                for (Account account : client.getBankAccountArrayList()) {
                    System.out.println("  " + i + ") " + account);
                    i++;
                }
            }
        }
        if (!exist) System.out.println("Клиент не существует!");
    }*/
}
