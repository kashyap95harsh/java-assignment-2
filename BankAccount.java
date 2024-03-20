class BankAccount {
     // Instance variables to store account information
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
      
    // Constructor to initialize the BankAccount object

    public BankAccount(String accountNumber, String accountHolderName, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
 // Override toString method to provide a string representation of the BankAccount object
    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
                "\nAccount Holder Name: " + accountHolderName +
                "\nAccount Type: " + accountType +
                "\nBalance: " + balance + "\n";
    }
}
