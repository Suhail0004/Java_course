package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static Account login(String accNum, String pin) {
        String query = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            System.out.println("DEBUG: Database connection successful");
            System.out.println("DEBUG: Attempting login with Account: " + accNum + ", PIN: " + pin);

            pstmt.setString(1, accNum);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("DEBUG: Login successful!");
                return new Account(rs.getString("account_number"), rs.getDouble("balance"));
            } else {
                System.out.println("DEBUG: No matching account found in database");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Database error during login:");
            e.printStackTrace();
        }
        return null;
    }

    public double checkBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            updateBalanceInDB(amount);
            System.out.println("Success! New Balance: " + this.balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Current Balance: " + balance);
        }
        updateBalanceInDB(-amount);
        System.out.println("Withdrawal Successful!.");
    }

    private void updateBalanceInDB(double amountToAdd) {
        String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDouble(1, amountToAdd);
            pstmt.setString(2, this.accountNumber);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                this.balance += amountToAdd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}