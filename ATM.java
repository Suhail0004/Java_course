package src;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        Account currentAccount = null;

        System.out.println("===  WELCOME TO GOPALGANJ ATM   ===");

      
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("\nEnter Account Number:  ");
            String accNum = scanner.next();
            System.out.print("Enter PIN:  ");
            String pin = scanner.next();

            currentAccount = Account.login(accNum, pin);

            if (currentAccount != null) {
                break; 
            } else {
                attempts++;
                System.out.println("Invalid Credentials. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (currentAccount == null) {
            System.out.println("Too many failed attempts.");
            System.exit(0);
        }

       
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + currentAccount.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depAmount = scanner.nextDouble();
                    currentAccount.deposit(depAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withAmount = scanner.nextDouble();
                    try {
                        currentAccount.withdraw(withAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using our GOPALGANJ ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}