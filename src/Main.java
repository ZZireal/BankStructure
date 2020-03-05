/*
Формулировка задания:
Счета.
1. Клиент может иметь несколько счетов в банке.
2. Учитывать возможность блокировки/разблокировки счета.
3. Реализовать поиск и сортировку счетов.
4. Вычисление общей суммы по счетам.
5. Вычисление суммы по всем счетам, имеющим положительный и отрицательный балансы отдельно.

Требования:
Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция.
- определить иерархию классов соответствующую вашей предметной области;
- каждый класс должен иметь отражающее смысл название и информативный состав;
- определить конструкторы и методы setТип(), getТип(), toString(), equals();
- наследование должно применяться только тогда, когда это имеет смысл.


Menu в класс работы с операциями, т.к. название.
2-я лаба: 1) создать класс банк вместо Menu, заполнить листы объектами (). Записать в файл, прочитать с файла (2 метода), используя сериализацию и десириализацию. +
          2) список клиентов, которые есть у банка, записать в файл другой, но в виде строки, и прочитать. +
          3) предусмотреть IO Exception, создать 1 класс, который будет наследоваться от Exception и наследовать лог. ошибку (нельзя, чтобы счет был отрицательным, выдать ошибку) +
*/

import Entity.Deserializer;
import Entity.NegativeCardAccountBalanceException;
import Entity.Serializer;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

class Main {

    public static void main(String[] args) {
        BankLogic bankLogic = new BankLogic();
        Serializer serializer = new Serializer();
        Deserializer deserializer = new Deserializer();
        Scanner scanner = new Scanner(System.in);

        try {
            bankLogic.initStartUsers();
        } catch (IOException | NegativeCardAccountBalanceException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("\n\nВыберите: ");
            System.out.println(
                    "1. Создать клиента" + "\n" +
                            "2. Создать счет" + "\n" +
                            "3. Просмотреть список клиентов" + "\n" +
                            "4. Просмотреть список счетов" + "\n" +
                            "5. Установить статус счета" + "\n" +
                            "6. Поиск счетов по имени клиента" + "\n" +
                            "7. Просмотреть отсортированные счета" + "\n" +
                            "8. Общая СУММА по счетам" + "\n" +
                            "9. СУММА по положительным и отрицательным счетам" + "\n" +
                            "10. Установить новый баланс спустя месяц" + "\n" +
                            "11. Сериализовать лист счетов (побайтово) и лист клиентов (в виде строки)" + "\n" +
                            "12. Десериализовать лист счетов (побайтово)" + "\n" +
                            "13. Десериализовать и вывести лист клиентов (в виде строки)" + "\n" +
                            "0. Выйти" + "\n");
            int chose = scanner.nextInt();
            try {
                switch (chose) {
                    case 1:
                        //создание ID клиента
                        Scanner scannerClient = new Scanner(System.in);
                        System.out.print("\nВведите ID клиента: ");
                        int identifier = Integer.parseInt(scannerClient.nextLine());

                        //проверка - если клиент существует
                        if (bankLogic.isClientExists(identifier)) {
                            System.out.print("Клиент уже существует!\n");
                            break;
                        }

                        //создание имени клиента
                        System.out.print("Введите имя клиента (или название организации): ");
                        String name = scannerClient.nextLine();

                        //создание типа клиента
                        System.out.print("Введите тип клиента (юридическое или физическое лицо): ");
                        String type = scannerClient.nextLine();

                        //создание типа клиента и соответствующего Person или Company, если не соответствует - break
                        if (type.equals("физическое")) {
                            System.out.print("Введите дату рождения клиента (ГГГГ-ММ-ДД): ");
                            String dateOfBirth = scannerClient.nextLine();
                            bankLogic.createPersonClient(identifier, type, name, dateOfBirth);
                            System.out.print("Клиент успешно создан!");
                            break;
                        } else if (type.equals("юридическое")) {
                            System.out.print("Введите дату регистрации клиента (ГГГГ-ММ-ДД): ");
                            String dateOfRegistration = scannerClient.nextLine();
                            bankLogic.createCompanyClient(identifier, type, name, dateOfRegistration);
                            System.out.print("Клиент успешно создан!");
                            break;
                        } else {
                            System.out.print("Некорретный тип клиента!");
                            break;
                        }
                    case 2:
                        //создание ID
                        Scanner scannerAccount = new Scanner(System.in);
                        System.out.println("\nВыберите ID клиента: ");
                        bankLogic.printClients(bankLogic.getClientList());

                        identifier = Integer.parseInt(scannerAccount.nextLine());

                        //проверка - если клиент не существует
                        if (!bankLogic.isClientExists(identifier)) {
                            System.out.print("Клиент не существует!\n");
                            break;
                        }

                        //выбор вида счета
                        System.out.println("\nВыберите вид счета:\n1. Карточный\n2. Депозитный\n3. Кредитный");
                        int choseKind = Integer.parseInt(scannerAccount.nextLine());
                        type = "";
                        double monthPayment = 0, percent = 0;

                        //в соответсвии с видом счета предлагает выбрать тип, процентную ставку/абон. плату
                        switch (choseKind) {
                            case 1:
                                System.out.print("Введите абонентскую плату: ");
                                monthPayment = Double.parseDouble(scannerAccount.nextLine());
                                break;
                            case 2:
                                System.out.print("Введите тип депозитного счета: ");
                                type = scannerAccount.nextLine();
                                System.out.print("Введите процентную ставку: ");
                                percent = Double.parseDouble(scannerAccount.nextLine());
                                break;
                            case 3:
                                System.out.print("Введите тип кредитного счета: ");
                                type = scannerAccount.nextLine();
                                System.out.print("Введите процентную ставку: ");
                                percent = Double.parseDouble(scannerAccount.nextLine());
                                break;
                            default:
                                System.out.println("Введены некорретные данные!");
                                continue;
                        }

                        System.out.print("Введите true или false, чтобы счет был активным или заблокированным: ");
                        boolean active = Boolean.parseBoolean(scannerAccount.nextLine());
                        try {
                            System.out.print("Введите сумму счета: ");
                            double balance = Double.parseDouble(scannerAccount.nextLine());
                            //отлавливаю пользовательское исключение (баланс карточного счета не может быть меньше 0)
                            if (choseKind == 1 && balance < 0) throw new NegativeCardAccountBalanceException(balance);

                            System.out.print("Введите номер счета: ");
                            String number = scannerAccount.nextLine();

                            //проверка - если счет существует
                            if (bankLogic.isAccountNumberExists(number)) {
                                System.out.print("Счет существует!\n");
                                break;
                            }

                            //проверяем, какой счет выбирали, и создаем соотв. счет
                            switch (choseKind) {
                                case 1:
                                    bankLogic.createCardAccount(identifier, active, balance, number, monthPayment);
                                    System.out.print("Счет успешно создан!");
                                    break;
                                case 2:
                                    bankLogic.createDepositAccount(identifier, active, balance, number, type, percent);
                                    System.out.print("Счет успешно создан!");
                                    break;
                                case 3:
                                    bankLogic.createCreditAccount(identifier, active, balance, number, type, percent);
                                    System.out.print("Счет успешно создан!");
                                    break;
                            }
                        } catch (NegativeCardAccountBalanceException ex) {
                            System.out.println("Отловлено пользовательское исключение! " + ex.toString());
                        }
                        break;
                    case 3:
                        //просмотр всех клиентов
                        bankLogic.printClients(bankLogic.getClientList());
                        break;
                    case 4:
                        //просмотр всех счетов
                        bankLogic.printAccounts(bankLogic.getAccountList());
                        break;
                    case 5:
                        //блокировка и разблокировка счета
                        Scanner scannerActive = new Scanner(System.in);

                        System.out.println("\nВыберите ID клиента: ");
                        bankLogic.printClients(bankLogic.getClientList());
                        identifier = Integer.parseInt(scannerActive.nextLine());

                        //проверка - если клиент не существует
                        if (!bankLogic.isClientExists(identifier)) {
                            System.out.print("Клиент не существует!\n");
                            break;
                        }

                        System.out.print("\nСчета клиента с именем '" + bankLogic.getNameOrTitleById(identifier) + "':\n");
                        bankLogic.printAccounts(bankLogic.getAccountsOfPerson(bankLogic.getNameOrTitleById(identifier)));

                        System.out.print("Введите номер счета: ");
                        String number = scannerActive.nextLine();

                        //проверка - если счет не существует
                        if (!bankLogic.isAccountNumberExists(number)) {
                            System.out.print("Счет не существует!\n");
                            break;
                        }

                        System.out.print("Введите новый статус: ");
                        boolean status = Boolean.parseBoolean(scannerActive.nextLine());

                        bankLogic.setActiveAccount(number, status);
                        System.out.print("Установлен статус " + status + " для счета " + number + "!");
                        break;
                    case 6:
                        //поиск счетов по имени клиента
                        try {
                            Scanner scannerSearch = new Scanner(System.in);
                            System.out.print("Введите имя (или название) клиента (или организации), счета которого необходимо вывести: ");
                            name = scannerSearch.nextLine();
                            if (bankLogic.getAccountsOfPerson(name) != null)
                                bankLogic.printAccounts(bankLogic.getAccountsOfPerson(name));
                            else System.out.print("Клиент не существует!");
                        } catch (Exception ex) {
                            System.out.println("Введены некорретные данные!");
                        }
                        break;
                    case 7:
                        //просмотр отсортированных счетов
                        bankLogic.printAccounts(bankLogic.getSortedAccounts());
                        break;
                    case 8:
                        //просмотр суммы всех счетов
                        System.out.print("Сумма всех счетов равна " + bankLogic.getSumAllAccounts() + ".");
                        break;
                    case 9:
                        //просмотр положительной и отрицательной сумм всех счетов
                        System.out.print("Сумма положительных счетов равна " + bankLogic.getSumPositiveAccounts() +
                                ".\nСумма отрицательных счетов равна " + bankLogic.getSumNegativeAccounts() + ".");
                        break;
                    case 0:
                        return;
                    case 10:
                        //установить новый баланс спустя месяц
                        bankLogic.setMonthChangedBalance();
                        System.out.print("Баланс всех счетов пересчитан и изменен!");
                        break;
                    case 11:
                        //сериализация
                        serializer.serializeAccountList(bankLogic.getAccountList());
                        serializer.serializeClientList(bankLogic.getClientList());
                        System.out.print("\nЛист счетов и клиентов сериализован!");
                        break;
                    case 12:
                        //десериализация
                        bankLogic.addInAccountList(deserializer.getDeserializeAccountList());
                        System.out.print("\nЛист счетов десереализован!");
                        break;
                    case 13:
                        //десериализовать и вывести десериализованные данные (в виде строки)
                        System.out.println(deserializer.getDeserializeClientList());
                        break;
                    default:
                        System.out.println("Введены некорретные данные!");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Введены некорректные данные!" + ex.getMessage());
            }
        }
    }
}

