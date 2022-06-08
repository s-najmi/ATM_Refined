import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String password;
    private long balance;
    private AccountType accountType;
    private List<Transaction> transactions = new ArrayList<>();
//    private static final long MIN_BALANCE = 100000;

    public Account(String password, String accountNumber, AccountType accountType, long balance) {
//        this.accountNumber = String.valueOf((int)(Math.random()*1000000));
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) throws Exception {
        if(balance<0){
            throw new Exception();
        }
        this.balance = balance;
    }

    public boolean passwordValidate(String password) throws Exception{
        if(password.equals(this.password)){
            throw new Exception();
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", transactions=" + transactions +
                '}';
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction tr) {
        transactions.add(tr);
    }
}
