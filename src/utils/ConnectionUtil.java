package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Conexiunea pentru prezentare proiect:
            Connection con = DriverManager.getConnection("jdbc:mysql://104.248.84.92/popa_andreea", "java", "Javaestetare123!");
            // Conexiunea localhost:
           // Connection con = DriverManager.getConnection("jdbc:mysql://localhost/loginapp", "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }
    }

}
