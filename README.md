# GOPALGANJ ATM - Java Console Application

An ATM system built with Java that simulates basic banking operations with MySQL database integration.

## Features

- **Secure Login System**: Account number and PIN-based authentication with 3 attempt limit
- **Balance Inquiry**: Check current account balance
- **Deposit Money**: Add funds to your account
- **Withdraw Money**: Withdraw funds with insufficient balance protection
- **Database Integration**: MySQL database for persistent data storage
- **Environment Configuration**: Secure credential management using `.env` files




3. Insert sample account data:

```sql
INSERT INTO accounts (account_number, pin, balance) 
VALUES ('1234567890', '1234', 5000.00);
```

4. **Compile the project**:
   ```bash
   javac -d bin -cp "lib/*" src/*.java
   ```

5. **Run the application**:
   ```bash
   java -cp "bin;lib/*" src.ATM
   ```


2. **Main Menu Options**:
   - `1` - Check Balance
   - `2` - Deposit Money
   - `3` - Withdraw Money
   - `4` - Exit

