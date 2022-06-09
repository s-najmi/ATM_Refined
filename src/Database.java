import java.sql.Connection;
import java.sql.Statement;

public class Database {
    Connection con = null;

    public Database(Connection con) {
        this.con = con;
        MakeDB();
    }

//    Account account = new Account(1234,AccountType.INTEREST_FREE_SAVING);
//    Person pr1 = new Person("Ali","Ahmadi","0060253615", new Account[] {account});
    private void MakeDB(){
        String sql ="CREATE SEQUENCE IF NOT EXISTS public.tbl_person_perso_id_seq\n" +
                "    INCREMENT 1\n" +
                "    START 1\n" +
                "    MINVALUE 1\n" +
                "    MAXVALUE 2147483647\n" +
                "    CACHE 1;\n" +
                "CREATE SEQUENCE IF NOT EXISTS public.tbl_transaction_transaction_id_seq\n" +
                "    INCREMENT 1\n" +
                "    START 1\n" +
                "    MINVALUE 1\n" +
                "    MAXVALUE 2147483647\n" +
                "    CACHE 1;" +
                "CREATE TABLE IF NOT EXISTS tbl_person \n" +
                "(perso_id integer NOT NULL DEFAULT nextval('tbl_person_perso_id_seq'::regclass), \n" +
                "name character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "family character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "nationalCode bigint NOT NULL,CONSTRAINT tbl_person_pkey PRIMARY KEY (perso_id)); \n" +
                "CREATE TABLE IF NOT EXISTS tbl_account \n" +
                "(accnumber character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "password character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "balance bigint NOT NULL,acctype bigint NOT NULL); \n" +
                "CREATE TABLE IF NOT EXISTS tbl_transaction( \n" +
                "transaction_id integer NOT NULL DEFAULT nextval('tbl_transaction_transaction_id_seq'::regclass), \n" +
                "date character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "amount bigint NOT NULL,account_id character varying COLLATE pg_catalog.default NOT NULL, \n" +
                "trtype char NOT NULL,CONSTRAINT tbl_transaction_pkey PRIMARY KEY (transaction_id));" +
                "INSERT INTO tbl_person(name, family,nationalCode) VALUES ('soheila', 'najmi', 1233211500);" +
                "INSERT INTO tbl_account(accnumber, password, balance, acctype) VALUES (123456789, 1234, 100000, 2);";
        try {
            //tbl_basket
            Statement pstmt = con.createStatement();
            int result = pstmt.executeUpdate(sql);
            if (result > 0) {
                System.out.println("DataBase got ready.");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
