package task2java;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<Transaction> transactions;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public boolean verifyCredentials(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }

    public void deposit(double amount, String date) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, date));
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount, String date) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, date));
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(double amount, Account recipient, String date) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount, date);
            transactions.add(new Transaction("Transfer to " + recipient.getUserId(), amount, date));
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void displayTransactions() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getUserId() {
        return userId;
    }
}

public class ATMsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account userAccount = new Account("user123", "1234", 1000.0);
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter user PIN: ");
        String userPin = scanner.nextLine();

        if (userAccount.verifyCredentials(userId, userPin)) {
            System.out.println("Welcome, " + userId + "!");
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        userAccount.displayTransactions();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        userAccount.withdraw(withdrawAmount, "Today");
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        userAccount.deposit(depositAmount, "Today");
                        break;
                    case 4:
                        System.out.print("Enter recipient's user ID: ");
                        String recipientId = scanner.nextLine();
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character

                        Account recipientAccount = new Account(recipientId, "", 0.0);
                        userAccount.transfer(transferAmount, recipientAccount, "Today");
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Exiting.");
        }

        scanner.close();
    }
}
