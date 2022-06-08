import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Statement extends Transaction {
    public Statement(Date dateTr, long amount, Account account, Connection con) {
        super(dateTr, 0, account, 'S', con);
    }

    @Override
    public void operate() throws Exception {
        long currentAmount = account.getBalance();
        try {
            long newAmount = currentAmount - this.getAmount();
            account.setBalance(newAmount);
            account.addTransaction(this);
            //super.addTrToDB(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(getTransactions());
    }
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Transaction tr = null;
        String sql = "select * from tbl_transaction where account_id=?";
        try{
            PreparedStatement pstmt = super.getCon().prepareStatement(sql);
            pstmt.setString(1, account.getAccountNumber());
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                switch(result.getString("trtype").charAt(0)){
                    case 'D':
                        tr = new Deposit(result.getDate("date"), result.getLong("amount"), account, null);
                        break;
                    case 'W':
                        tr = new Withdrawal(result.getDate("date"), result.getLong("amount"), account, null);
                        break;
                    default:
                        tr = new Statement(result.getDate("date"), result.getLong("amount"), account, null);
                };
                transactions.add(tr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
