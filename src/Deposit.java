import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;

public class Deposit extends Transaction {
    public Deposit(Date dateTr, long amount, Account account, Connection con) {
        super(dateTr, amount, account,'D', con);
    }

    @Override
    public void operate() throws Exception {
        long currentAmount = account.getBalance();
        long newAmount = currentAmount + this.getAmount();
        account.setBalance(newAmount);
        account.addTransaction(this);
        super.addTrToDB(this);
    }
}
