import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Transaction {
    private Date dateTr;
    private long amount;
    protected Account account;
    private char trType;
    private Connection con;

    public Transaction(Date dateTr, long amount, Account account, char trType, Connection con) {
        this.dateTr = dateTr;
        this.amount = amount;
        this.account = account;
        this.trType = trType;
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public Date getDateTr() {
        return dateTr;
    }

    public void setDateTr(Date dateTr) {
        this.dateTr = dateTr;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateTr=" + dateTr +
                ", amount=" + amount +
                ", trType=" + trType +
                '}';
    }

    public abstract void operate() throws Exception;

    public void addTrToDB(Transaction tr) {
        long dbId = 0;
        String sql1 = "INSERT INTO public.tbl_transaction(date, amount, account_id, trtype) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setDate(1, tr.getDateTr());
            pstmt.setLong(2,tr.getAmount());
            pstmt.setString(3, tr.getAccount().getAccountNumber());
            pstmt.setString(4,String.valueOf(this.trType));
            int result = pstmt.executeUpdate();
            if (result > 0) {
                try (ResultSet res = pstmt.getGeneratedKeys()) {
                    if (res.next()) {
                        dbId = res.getLong("transaction_id");
                    }
                }
            }
            System.out.println("Transaction "+ dbId + " is done successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}