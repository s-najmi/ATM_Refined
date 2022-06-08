import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagment {

    public Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/ATM";
        String user = "postgres";
        String password = "123456";
        Connection conn;

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conected...");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
