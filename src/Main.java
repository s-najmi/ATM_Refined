import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectionManagment conn = new ConnectionManagment();
        Connection con = conn.connect();
        Database db = new Database(con);
        ATM atm = new ATM(con);
        try {
            atm.run();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
