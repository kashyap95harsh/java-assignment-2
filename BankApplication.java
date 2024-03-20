import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplication {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadAccountsFromFile("bank_accounts.csv");
        displayMenu();
    }

    private static void loadAccountsFromFile(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
           int i = 0;
            while ((line = br.readLine()) != null) {
                if (i==0){
                    i++; 
                    continue;}
 String[] data = line.split(",");
                if (data.length == 4) {
                    BankAccount account = new BankAccount(data[0], data[1], data[2], Double.parseDouble(data[3]));
                    accounts.add(account);
                } else {
                    System.err.println("Invalid data found in CSV: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
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

    private static void viewAllAccounts() {
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    private static void searchAccountByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNumber)) {
                System.out.println("Account found:\n" + account);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private static void depositMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
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

    private static void withdrawMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
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
        System.out.println("Account not found.");
    }
}
