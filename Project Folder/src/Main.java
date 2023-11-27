import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;
 
//author : Austin Wort

public class Main { 
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
 
       Class.forName("oracle.jdbc.driver.OracleDriver");
       //Change user and password to be your username and password
       //Change SID (orcl) to your respective SID, probably either xe or orcl
       Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl","user", "pass");
       if(conn != null){
        System.out.println("Connected Succesfully");
       }

       Scanner scanner = new Scanner(System.in);
    
       //menu formatting
       while(true){

        System.out.println("********************************************");
        System.out.println("Welcome to the Retail Store DBMS Program");
        System.out.println("Please enter an input");
        System.out.println("--------------------------------------------");
        System.out.println("1 : Create Tables");
        System.out.println("2 : Drop Tables");
        System.out.println("3 : Populate Tables");
        System.out.println("4 : Query Tables");
        System.out.println("5 : Exit Menu");
        System.out.println("********************************************");

        String input = scanner.nextLine();

        if(input.equals("1")){
        CreateTables.createAll(conn);
        System.out.println("Tables Created Succesfully");
        }

        if(input.equals("2")){
        DropTables.DropAll(conn);
        System.out.println("Tables Dropped Succesfully");    
        }

        if(input.equals("3")){
        PopulateTables.populateAll(conn);
        System.out.println("Tables Populated Succesfully");    
        }

        if(input.equals("4")){
        QueryTables.queryAll(conn);
        System.out.println("Database Queries: ");  
        }

        if(input.equals("5")){
        conn.close();
        System.err.println("Database Connection Closed");
        break;
        }
    }         
  }
}