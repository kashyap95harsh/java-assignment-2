import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {
    private static ArrayList<BankAccount> accounts = new ArrayList<>(); //this array is storing bank accounts in it

    public static void main(String[] args) {
        loadAccountsFromFile("bank_accounts.csv"); //loading bank accounts in the given assingment file (bank_accounts.csv)
        //method to display the menu
        displayMenu();  
    }
      // Method to load accounts from a CSV file
    private static void loadAccountsFromFile(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
           int i = 0;
             // Reading each line from the file 
            while ((line = br.readLine()) != null) {
                // there was a header which was supposed to be be skip using this if statements 
                if (i==0){
                    i++; 
                    continue;} 
                    // splitting the line into different data fields 
 String[] data = line.split(",");
                if (data.length == 4) {
                    // Create a BankAccount object and add it to the accounts list
                    BankAccount account = new BankAccount(data[0], data[1], data[2], Double.parseDouble(data[3]));
                    accounts.add(account);
                } else {
                    // Print error message
                    System.err.println("Invalid data found in CSV: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Handle file reading or number formatting errors
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

      // Method to display the main menu
    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        // Displaying menu options until the user chooses to exit using a loop for that 
        do {
            System.out.println("1. Show all bank accounts");
            System.out.println("2. Search for an account by account number");
            System.out.println("3. Deposit money into an account");
            System.out.println("4. Withdraw money from an account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        viewAllAccounts();
                        break;
                    case 2:
                        searchAccountByNumber();
                        break;
                    case 3:
                        depositMoney();
                        break;
                    case 4:
                        withdrawMoney();
                        break;
                    case 0:
                        System.out.println("Thank You for using our service.\nExiting..");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
                choice = -1;
            }
        } while (choice != 0);
        scanner.close();
    }
  // Method to display all bank accounts
    private static void viewAllAccounts() {
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }
 // Method to search for an account by account number
    private static void searchAccountByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        // Iterate through accounts to find the specified account
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNumber)) {
                System.out.println("Account found:\n" + account);
                return;
            }
        }
        System.out.println("Account not found."); // Print message if account is not found
    }
//method to deposit money into an account
    private static void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        // Iterate through accounts to find the specified account
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNumber)) {
                System.out.print("Enter amount to deposit: ");
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    account.setBalance(account.getBalance() + amount);
                    System.out.println("Deposit successful. New balance: " + account.getBalance());
                } else {
                    System.out.println("Invalid amount.");
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }
// Method to withdraw money from an account
    private static void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        // Iterate through accounts to find the specified account
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNumber)) {
                System.out.print("Enter amount to withdraw: ");
                double amount = scanner.nextDouble();
                if (amount > 0 && amount <= account.getBalance()) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                } else {
                    System.out.println("Invalid amount or insufficient balance.");
                }
                return;
            }
        }
        System.out.println("Account not found.");        // Print message if account is not found
    }
}
