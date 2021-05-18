package database;

import java.sql.*;

public class DatabaseInit {

    public static void createDatabase(){

        String url = "jdbc:sqlite:E:/ProjectMIZU/databaseTrial/databaseTrial" ;


        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTable()
    {
        String url = "jdbc:sqlite:E:/ProjectMIZU/databaseTrial/databaseTrial";

        String workerTable = "CREATE TABLE IF NOT EXISTS workers(\n" +
                "   workerId INTEGER PRIMARY KEY,\n" +
                "   name TEXT NOT NULL,\n" +
                "   phoneNo TEXT,\n" +
                "   address TEXT,\n"+
                "   nid  INT ,\n"+
                "   submissionNo INT NOT NULL\n" +
                ");";

        String productTable = "CREATE TABLE IF NOT EXISTS products(\n" +
                "   productID INTEGER PRIMARY KEY,\n" +
                "   name TEXT NOT NULL,\n" +
                "   rate REAL NOT NULL,\n" +
                "   color TEXT,\n" +
                "   size TEXT NOT NULL,\n" +
                "   rattler TEXT,\n" +
                "   notes TEXt\n" +
                ");";

        String submissionTable = "CREATE TABLE IF NOT EXISTS submissions(\n" +
                "   submissionID INTEGER PRIMARY KEY,\n" +
                "   submitTime TEXT NOT NULL,\n" +
                "   workerID INT NOT NULL,\n" +
                "   productID INT NOT NULL,\n" +
                "   productNo INT,\n" +
                "   paid TEXT,\n" +
                "   FOREIGN KEY (workerID)\n"+
                "       REFERENCES workers(workerID)\n"+
                "   FOREIGN KEY (productID)\n"+
                "       REFERENCES products(productID)\n"+
                ");";

        String paymentTable = "CREATE TABLE IF NOT EXISTS payments(\n" +
                "paymentID INTEGER PRIMARY KEY,\n" +
                "timestamp TEXT NOT NULL,\n" +
                "workerID INT NOT NULL,\n" +
                "submissionList TEXT NOT NULL,\n" +
                "totalSubmissions INT NOT NULL,\n" +
                "amountPaid REAL NOT NULL,\n" +
                "FOREIGN KEY (workerID)\n" +
                "   REFERENCES workers(workerID)\n" +
                ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement statement = conn.createStatement();

            statement.execute(workerTable);
            statement.execute(productTable);
            statement.execute(submissionTable);
            statement.execute(paymentTable);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }


    public static void main (String[] args){
        createDatabase();
        createTable();
    }

}
