public interface IWorkable {
    void createBankClient();
    void createDepositAccount();
    //void createCreditAccount();
    //void createCardAccount();
    void getBankClients();
    void getAllBankAccounts();
    void setActiveAccount();
    void searchAccountOfPerson();
    void getAllSortedAccounts();
    void getSumAllAccounts();
    void getSumPositiveNegativeAccounts();
}
