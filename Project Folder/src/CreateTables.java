import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//author : Austin Wort

public class CreateTables {

    public static void createAll(Connection conn) throws SQLException{
        createClthngTable(conn);
        createCustTable(conn);
        createEmpTable(conn);
        createMngrTable(conn);
        createSuppTable(conn);
        createTrnsctTable(conn);
        createWrkrTable(conn);
        AlterTables(conn);
    }
 
    public static void createClthngTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query = "CREATE TABLE clthng ("
         +"itemnum INTEGER NOT NULL,"
         +"supp_supplierid    INTEGER NOT NULL,"
         +"supp_manager_empid INTEGER NOT NULL,"
         +"price              NUMBER,"
         +"quantity           INTEGER,"
         +"design             VARCHAR2(40 CHAR)"
         +")";

        Statement stm2 = conn.createStatement();
        String query2 = "ALTER TABLE clthng"
        +" ADD CONSTRAINT clothing_pk PRIMARY KEY ( itemnum,"
        +"supp_supplierid,"
        +"supp_manager_empid )";

         stm1.execute(query);
         stm2.execute(query2);
         stm1.close();
         stm2.close();
    }

    public static void createCustTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query  = "CREATE TABLE cust ("
        +"customerid INTEGER NOT NULL,"
        +"wrkr_empid INTEGER NOT NULL,"
        +"phonenum   VARCHAR2(40 CHAR),"
        +"name       VARCHAR2(40 CHAR)"
        +")";

        Statement stm2 = conn.createStatement();
        String query2 = "ALTER TABLE cust ADD CONSTRAINT customer_pk PRIMARY KEY ( customerid,"
        +"wrkr_empid )";
      

         stm1.execute(query);
         stm2.execute(query2);
         stm1.close();
         stm2.close();

    }

    public static void createEmpTable(Connection conn) throws SQLException{

        Statement stm1  = conn.createStatement();
        String query = "CREATE TABLE emp("
        +"empid INTEGER NOT NULL,"
        +"username VARCHAR2(40 CHAR),"
        +"password VARCHAR2(40 CHAR),"
        +"type VARCHAR2(20 CHAR)"
        +")";

        Statement stm2 = conn.createStatement();
        String query2  = "ALTER TABLE emp ADD CONSTRAINT emp_pk PRIMARY KEY ( empid )";
     
         stm1.execute(query);
         stm2.execute(query2);
         stm1.close();
         stm2.close();

    }

    public static void createMngrTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query = "CREATE TABLE mngr ("
        +"empid        INTEGER NOT NULL,"
        +"mngr_empid   INTEGER NOT NULL,"
        +"name         VARCHAR2(20 CHAR),"
        +" salary       NUMBER,"
        +" hoursperweek NUMBER"
        +")";

        Statement stm2 = conn.createStatement();
        String query2 = "CREATE UNIQUE INDEX manager__idx ON"
        + " mngr ("
        +" mngr_empid"
        +" ASC )";

        Statement stm3 = conn.createStatement();
        String query3 = "ALTER TABLE mngr ADD CONSTRAINT manager_pk PRIMARY KEY ( empid )";

        stm1.execute(query);
        stm2.execute(query2);
        stm3.execute(query3);
        stm1.close();
        stm2.close();
        stm3.close();

    }

    public static void createSuppTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query = "CREATE TABLE supp ("
        +" supplierid   INTEGER NOT NULL,"
        +" mngr_empid   INTEGER NOT NULL,"
        +" suppliername VARCHAR2(40 CHAR),"
        +" itemtype     VARCHAR2(40 CHAR)"
        +")";

        Statement stm2 = conn.createStatement();
        String query2 = "ALTER TABLE supp ADD CONSTRAINT supplier_pk PRIMARY KEY ( supplierid,"
        +" mngr_empid )";

        stm1.execute(query);
        stm2.execute(query2);
        stm1.close();
        stm2.close();
    }

    public static void createTrnsctTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query = "CREATE TABLE trnsct ("
        +" ccnumber          INTEGER NOT NULL,"
        +"cust_customerid   INTEGER NOT NULL,"
        +"cust_worker_empid INTEGER NOT NULL"
        +")";

        Statement stm2 = conn.createStatement();
        String query2 = "ALTER TABLE trnsct"
        +" ADD CONSTRAINT transaction_pk PRIMARY KEY ( ccnumber,"
        +" cust_customerid,"
        +"cust_worker_empid )";

        stm1.execute(query);
        stm2.execute(query2);
        stm1.close();
        stm2.close();
    }

    public static void createWrkrTable(Connection conn) throws SQLException{

        Statement stm1 = conn.createStatement();
        String query = "CREATE TABLE wrkr ("
        +" empid        INTEGER NOT NULL,"
        +"wrkr_empid   INTEGER NOT NULL,"
        +" name         VARCHAR2(40 CHAR),"
        +" wage         NUMBER,"
        +" hoursperweek NUMBER"
        +")";

        Statement stm2 = conn.createStatement();
        String query2 = "CREATE UNIQUE INDEX worker__idx ON"
        +" wrkr ("
        +"wrkr_empid"
        +" ASC )";

        Statement stm3 = conn.createStatement();
        String query3 = "ALTER TABLE wrkr ADD CONSTRAINT worker_pk PRIMARY KEY ( empid )";

        stm1.execute(query);
        stm2.execute(query2);
        stm3.execute(query3);
        stm1.close();
        stm2.close();
        stm3.close();
    }

    public static void AlterTables(Connection conn) throws SQLException{
        Statement stm1 = conn.createStatement();
        String query = """
        ALTER TABLE clthng
    ADD CONSTRAINT clthng_supp_fk FOREIGN KEY ( supp_supplierid,
                                                supp_manager_empid )
        REFERENCES supp ( supplierid,
                          mngr_empid );

ALTER TABLE cust
    ADD CONSTRAINT cust_wrkr_fk FOREIGN KEY ( wrkr_empid )
        REFERENCES wrkr ( empid );

ALTER TABLE mngr
    ADD CONSTRAINT manager_employee_fk FOREIGN KEY ( empid )
        REFERENCES emp ( empid );

ALTER TABLE mngr
    ADD CONSTRAINT mngr_mngr_fk FOREIGN KEY ( mngr_empid )
        REFERENCES mngr ( empid );

ALTER TABLE supp
    ADD CONSTRAINT supp_mngr_fk FOREIGN KEY ( mngr_empid )
        REFERENCES mngr ( empid );

ALTER TABLE trnsct
    ADD CONSTRAINT trnsct_cust_fk FOREIGN KEY ( cust_customerid,
                                                cust_worker_empid )
        REFERENCES cust ( customerid,
                          wrkr_empid );

ALTER TABLE wrkr
    ADD CONSTRAINT worker_employee_fk FOREIGN KEY ( empid )
        REFERENCES emp ( empid );

ALTER TABLE wrkr
    ADD CONSTRAINT wrkr_wrkr_fk FOREIGN KEY ( wrkr_empid )
        REFERENCES wrkr ( empid );

CREATE OR REPLACE TRIGGER arc_fkarc_1_wrkr BEFORE
    INSERT OR UPDATE OF empid ON wrkr
    FOR EACH ROW
DECLARE
    d VARCHAR2(20 CHAR);
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        emp a
    WHERE
        a.empid = :new.empid;

    IF ( d IS NULL OR d <> 'WRKR' ) THEN
        raise_application_error(-20223, 'FK worker_employee_FK in Table WRKR violates Arc constraint on Table EMP - discriminator column type doesn''t have value ''WRKR'''
        );
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_1_mngr BEFORE
    INSERT OR UPDATE OF empid ON mngr
    FOR EACH ROW
DECLARE
    d VARCHAR2(20 CHAR);
BEGIN
    SELECT
        a.type
    INTO d
    FROM
        emp a
    WHERE
        a.empid = :new.empid;

    IF ( d IS NULL OR d <> 'MNGR' ) THEN
        raise_application_error(-20223, 'FK manager_employee_FK in Table MNGR violates Arc constraint on Table EMP - discriminator column type doesn''t have value ''MNGR'''
        );
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/
""";

    }

}
