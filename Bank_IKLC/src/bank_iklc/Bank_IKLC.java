/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank_iklc;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bank_IKLC {

    private Account[] accounts;
    private int size;
    private int lastAccountNumber;

    public Bank_IKLC(int kapasitas) {
        this.accounts = new Account[kapasitas];
        this.size = 0;
        this.lastAccountNumber = 99999; // Nomor akun terakhir yang digunakan sebelum program dijalankan
    }

    

    private int generateAccountNumber() {
        int accountNumber = this.lastAccountNumber + 1;
        this.lastAccountNumber = accountNumber;
        return accountNumber;
    }

    public static LocalDateTime getLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime;
    }
    public void register() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = input.nextDouble();

        int accountNumber = generateAccountNumber();

        Account account = new Account(accountNumber, name, balance);
        this.accounts[this.size] = account;
        this.size++;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        System.out.println("Registration time : " + formattedNow);
        System.out.println("Account created. Your account number is " + accountNumber + ".");
        
    }

     
     
    public void transfer() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter source account number: ");
        int sourceNumber = input.nextInt();
        Account sourceAccount = getAccount(sourceNumber);

        if (sourceAccount == null) {
            System.out.println("Source account not found.");
            return;
        }

        System.out.print("Enter destination account number: ");
        int destNumber = input.nextInt();
        Account destAccount = getAccount(destNumber);

        if (destAccount == null) {
            System.out.println("Destination account not found.");
            return;
        }

         double amount ;
         do {
                System.out.print ("Enter transfer amount: ");
                if (input.hasNextDouble()) {
                    amount= input.nextDouble();
                    break;
                } else {
                    System.out.println("Input is not a number. Please try again.");
                    input.next();
                            
                }
            } while (true);

        if (sourceAccount.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        sourceAccount.withdraw(amount);
        destAccount.deposit(amount);
        System.out.println("Transfer successful.");
        System.out.println("Source account:");
        System.out.println(sourceAccount);
        System.out.println("Destination account:");
        System.out.println(destAccount);
    }

    public void deposit() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter account number: ");
        int accountNumber = input.nextInt();
        Account account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        double amount ;
         do {
                System.out.print ("Enter deposit amount: ");
                if (input.hasNextDouble()) {
                    amount= input.nextDouble();
                    break;
                } else {
                    System.out.println("Input is not a number. Please try again.");
                    input.next();
                            
                }
            } while (true);
        account.deposit(amount);
        System.out.println("Deposit successful.");
        System.out.println(account);
    }

    public void withdraw() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter account number: ");
        int accountNumber = input.nextInt();
        Account account = getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
         double amount ;
         do {
                System.out.print ("Enter withdraw amount: ");
                if (input.hasNextDouble()) {
                    amount= input.nextDouble();
                    break;
                } else {
                    System.out.println("Input is not a number. Please try again.");
                    input.next();
                            
                }
            } while (true);

        if (account.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
        System.out.println(account);
    }

    public void displayAccounts() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(accounts[i]);
        }
    }

    

private Account getAccount(int accountNumber) {
    for (int i = 0; i < this.size; i++) {
        if (this.accounts[i].getNumber() == accountNumber) {
            return this.accounts[i];
        }
    }
    return null;
}

public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Bank_IKLC bank = new Bank_IKLC(100);

    while (true) {
        System.out.println("\nBank Menu:");
        System.out.println("1. Register ");
        System.out.println("2. Transfer ");
        System.out.println("3. Deposit ");
        System.out.println("4. Withdraw ");
        System.out.println("5. Display accounts");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                bank.register();
                break;
            case 2:
                bank.transfer();
                break;
            case 3:
                bank.deposit();
                break;
            case 4:
                bank.withdraw();
                break;
            case 5:
                bank.displayAccounts();
                break;
            case 6:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}
}

    class Account {
    private int number;
    private String name;
    private double balance;

    public Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Account " + this.number + " (" + this.name + "): Rp." + this.balance;
    }
    }

       
