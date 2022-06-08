import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ATM {
    private long amount;
    private int decision;
    private String accNo = null;
    private String pass = null;
    private Connection con;
    private Account acc;

    public ATM(Connection con) {
        this.con = con;
    }


    public void showMenue1() {
        System.out.println("Enter your account number:");
        Inputs inp = new Inputs();
        try {
            accNo = (String) inp.getInput(String.class);
        }catch (Exception e){
            e.getMessage();
        }
        System.out.println("Enter your password:");
        inp = new Inputs();
        try {
            pass = (String) inp.getInput(String.class);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void showMenue2() {
        System.out.println("Choose one of the actions:");
        System.out.println("1. Deposite");
        System.out.println("2. Withdrawal");
        System.out.println("3. List of Transactions");
        System.out.println("4. Exit");
        Inputs inp = new Inputs();
        try {
            decision = (Integer) inp.getInput(Integer.class);
            if (decision != 4 && decision != 3){
                System.out.println("Enter Amount:");
                amount = (Long) inp.getInput(Long.class);
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void run() throws Exception{
        showMenue1();
        if(login(accNo,pass) == false) throw new Exception(CustomizedException.Err_LOGIN.getMessage());
        else {
            boolean flag = true;
            while (flag) {
                showMenue2();
                Transaction tr;
                long millis=System.currentTimeMillis();

                switch (decision) {
                    case 1:
                        tr = new Deposit(new java.sql.Date(millis), amount, acc, con);
                        tr.operate();
                        break;
                    case 2:
                        tr = new Withdrawal(new java.sql.Date(millis), amount, acc, con);
                        tr.operate();
                        break;
                    case 3:
                        tr = new Statement(new java.sql.Date(millis), amount, acc, con);
                        tr.operate();
                        break;
                    case 4:
                        System.out.println("Bye Bye...");
                        flag = false;
                        break;
                    default:
                        throw new Exception(CustomizedException.Err_Input.getMessage());
                }
                System.out.println("Balance = " + acc.getBalance());
            }
        }
    }
    public boolean login(String user,String pass) throws Exception{
//        if (!user.equals(db.account.getAccountNumber()) || !db.account.passwordValidate(pass)){
//            throw new Exception(CustomizedException.Err_LOGIN.getMessage());
//        }
        String result;
        String sql = "select * from tbl_account where accNumber=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,accNo);
            ResultSet res = pstmt.executeQuery();
            if (res.next()){
                acc = new Account(res.getString("password"),res.getString("accNumber"),
                        AccountType.values()[(int) res.getLong("accType")],res.getLong("balance"));

                if(!acc.getPassword().equals(pass)){
                    return false;
                }
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
