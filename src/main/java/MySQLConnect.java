import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class MySQLConnect {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/rozetka";
    private static final String user = "root";
    private static final String password = "";

    public static Connection con;
    public static Statement stat;

    public void Connect()  {
        try {
            con = DriverManager.getConnection(url, user, password);
            stat = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } /*finally {
            try {
                con.close();
            } catch (SQLException se) {

            }*/


        }


    }

