import java.sql.Connection;
import java.sql.Date;

public class Withdrawal extends Transaction {
    public Withdrawal(Date dateTr, long amount, Account account, Connection con) {
        super(dateTr, amount, account,'W',con);
    }

    @Override
    public void operate() throws Exception {
        long currentAmount = account.getBalance();
        try {
            long newAmount = currentAmount - this.getAmount();
            account.setBalance(newAmount);
            account.addTransaction(this);
            super.addTrToDB(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
