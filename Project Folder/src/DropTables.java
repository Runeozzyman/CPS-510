import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//author : Austin Wort

public class DropTables {
    
    public static void DropAll(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        try {
            String[] dropStatements = {
                "DROP TABLE EMP CASCADE CONSTRAINTS",
                "DROP TABLE WRKR CASCADE CONSTRAINTS",
                "DROP TABLE MNGR CASCADE CONSTRAINTS",
                "DROP TABLE CUST CASCADE CONSTRAINTS",
                "DROP TABLE TRNSCT CASCADE CONSTRAINTS",
                "DROP TABLE SUPP CASCADE CONSTRAINTS",
                "DROP TABLE CLTHNG CASCADE CONSTRAINTS"
            };
        
            for (String dropStatement : dropStatements) {
                stm1.execute(dropStatement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stm1.close();
        }

        }
}
