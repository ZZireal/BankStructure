/*
Формулировка задания:
Счета.
1. Клиент может иметь несколько счетов в банке. +
2. Учитывать возможность блокировки/разблокировки счета. +
3. Реализовать поиск и сортировку счетов. +
4. Вычисление общей суммы по счетам. +
5. Вычисление суммы по всем счетам, имеющим положительный и отрицательный балансы отдельно. +

Требования:
Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция. +
- определить иерархию классов соответствующую вашей предметной области; +
- каждый класс должен иметь отражающее смысл название и информативный состав; +
- определить конструкторы и методы setТип(), getТип(), toString(), equals(); +
- наследование должно применяться только тогда, когда это имеет смысл. +


Menu в класс работы с операциями, т.к. название.
2-я лаба: 1) создать класс банк вместо Menu, заполнить листы объектами (). Записать в файл, прочитать с файла (2 метода), используя сериализацию и десириализацию.
          2) список клиентов, которые есть у банка, записать в файл другой, но посимвольно, и прочитать.
          3) предусмотреть IO Exception, создать 1 класс, который будет наследоваться от Exception и наследовать лог. ошибку (нельзя, чтобы счет был отрицательным, выдать ошибку)
*/

import java.util.*;

class Main {

    public static void main(String[] args) {
        BankLogic bankLogic = new BankLogic();
        Scanner scanner = new Scanner(System.in);
        bankLogic.initStartUsers();

        boolean consol = true;
        while (consol) {
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
                                "0. Выйти" + "\n");
                int chose = scanner.nextInt();
                switch (chose) {
                    case 1:
                        //создание клиента
                        Scanner scannerClient = new Scanner(System.in);
                        System.out.print("\nВведите ID клиента: ");
                        int identifier = Integer.parseInt(scannerClient.nextLine());

                        //проверка - если клиент существует
                        if (bankLogic.ifClientExists(identifier)) {
                            System.out.print("Клиент уже существует!\n");
                            break;
                        }

                        System.out.print("Введите ФИО клиента: ");
                        String name = scannerClient.nextLine();

                        System.out.print("Введите тип клиента (юридическое лицо или физическое лицо): ");
                        String type = scannerClient.nextLine();
                        if (type.equals("физическое")) {
                            System.out.print("Введите дату рождения клиента (ГГГГ-ММ-ДД): ");
                            String dateOfBirth = scannerClient.nextLine();
                            bankLogic.createPersonClient(identifier, name, type, dateOfBirth);
                            break;
                        } else
                            if (type.equals("юридическое")) {
                                System.out.print("Введите дату регистрации клиента (ГГГГ-ММ-ДД): ");
                                String dateOfRegistration = scannerClient.nextLine();
                                bankLogic.createCompanyClient(identifier, name, type, dateOfRegistration);
                                break;
                            }
                            else {
                                System.out.print("Некорретный тип клиента!");
                                break;
                            }
                    case 2:
                        //создание счета
                        bankLogic.createDepositAccount();
                        break;
                    case 3:
                        //просмотр всех клиентов
                        bankLogic.getClients();
                        break;
                    case 4:
                        //просмотр всех счетов
                        bankLogic.getAllBankAccounts();
                        break;
                    case 5:
                        //блокировка и разблокировка счета
                       // menu.setActiveAccount();
                        break;
                    case 6:
                        //поиск счетов по имени клиента
                     //   menu.searchAccountOfPerson();
                        break;
                    case 7:
                        //просмотр отсортированных счетов
                        bankLogic.getAllSortedAccounts();
                        break;
                    case 8:
                        //просмотр суммы всех счетов
                        bankLogic.getSumAllAccounts();
                        break;
                    case 9:
                        //просмотр положительной и отрицательной сумм всех счетов
                        bankLogic.getSumPositiveNegativeAccounts();
                        break;
                    case 0:
                        consol = false;
                        break;
                    default:
                        System.out.println("Введены некорретные данные!");
                }
        }
    }
}

