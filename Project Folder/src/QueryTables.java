import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//author : Austin Wort

public class QueryTables {

    public static void queryAll(Connection conn) throws SQLException{

            q1(conn);
            q2(conn);
            q3(conn);
            q4(conn);
            q5(conn);
            q6(conn);
            q7(conn);
            q8(conn);

    }
    
    public static void q1(Connection conn) throws SQLException{

        System.out.println("All Employees, grouped by type:");

        String q = "SELECT COUNT(EMPID), TYPE\n"
        +"FROM EMP\n"
        +"GROUP BY TYPE";

        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void q2(Connection conn) throws SQLException{

        System.out.println("Managers listed in order of their salary");

        String q = "SELECT * FROM MNGR\n"
        +"ORDER BY SALARY";

        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void q3(Connection conn) throws SQLException{

        System.out.println("Count of CustomerID values associated with the worker employeeID");

        String q = "SELECT COUNT(CUSTOMERID), WRKR_EMPID\n"
        +"FROM CUST\n"
        +"GROUP BY WRKR_EMPID";

        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void q4(Connection conn) throws SQLException{

        System.out.println("Groups transactions by customerID");

        String q = "SELECT * FROM TRNSCT\n"
        +"ORDER BY CUST_CUSTOMERID";

        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void q5(Connection conn) throws SQLException{

        System.out.println("Finds items priced over 100$");

        String q = "SELECT * FROM CLTHNG WHERE PRICE >100";
       
        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void q6(Connection conn) throws SQLException{

        System.out.println("Lists names of all Workers");

        String q = "SELECT name\n"
        +"FROM wrkr";
       
        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void q7(Connection conn) throws SQLException{

        System.out.println("Lists the number of Managers");

        String q = "SELECT COUNT(*) AS number_of_managers\n"
        +"FROM mngr";
       
        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void q8(Connection conn) throws SQLException{

        System.out.println("Lists all suppliers that supply jeans");

        String q = "SELECT suppliername\n"
        +"FROM supp\n"
        +"WHERE itemtype = 'Jeans'";
       
        PreparedStatement stm = conn.prepareStatement(q);

        ResultSet rs = stm.executeQuery();

        ResultSetMetaData data = rs.getMetaData();
        int fieldNumb = data.getColumnCount();
        
        while (rs.next()){
            for(int i =1;i<=fieldNumb;i++){
                if (i>1){
                    
                    System.out.print(" | ");
                    
                }
                System.out.print(data.getColumnName(i)+": ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }


}
