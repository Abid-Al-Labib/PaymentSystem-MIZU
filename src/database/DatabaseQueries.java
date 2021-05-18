package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseQueries {

   private Connection connect()
   {
       String url = "jdbc:sqlite:E:/ProjectMIZU/databaseTrial/databaseTrial";

       Connection conn = null;
       Properties properties = new Properties();
       properties.setProperty("foreign_keys","ON");

       try {
            conn = DriverManager.getConnection(url,properties);
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());
       }

       return conn;
   }





    public void addEmployee(String name, String phoneNo, String address, int nid, int submissionNo)
    {
        String sql = "INSERT INTO workers(name,phoneNo,address,nid,submissionNo) VALUES(?,?,?,?,?)";


        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setString(2,phoneNo);
            ps.setString(3,address);
            ps.setInt(4,nid);
            ps.setInt(5,submissionNo);

            System.out.println(ps.toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProduct(String name, double rate, String color, String size, String rattler, String notes){
        String sql = "INSERT INTO products(name,rate,color,size,rattler,notes) VALUES(?,?,?,?,?,?)";


        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setDouble(2,rate);
            ps.setString(3,color);
            ps.setString(4,size);
            ps.setString(5,rattler);
            ps.setString(6,notes);

            System.out.println(ps.toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addSubmission(int workerID, int productID, int productNo, String paid){
        String sql = "INSERT INTO submissions(submitTime,workerID,productID,productNo,paid) VALUES(?,?,?,?,?)";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,ts);
            ps.setInt(2,workerID);
            ps.setInt(3,productID);
            ps.setInt(4,productNo);
            ps.setString(5,paid);


            System.out.println(ps.toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addPayment(int workerID, String submissionList, int totalSubmissions, double amountPaid){
        String sql = "INSERT INTO payments(timestamp,workerID,submissionList,totalSubmissions,amountPaid) VALUES(?,?,?,?,?)";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,ts);
            ps.setInt(2,workerID);
            ps.setString(3,submissionList);
            ps.setInt(4,totalSubmissions);
            ps.setDouble(5,amountPaid);


            System.out.println(ps.toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllEmployees(){
        String sql = "SELECT * FROM workers";

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {
                System.out.println(
                                    rs.getInt("workerID")+ "\t" +
                                    rs.getString("name")+ "\t" +
                                    rs.getString("phoneNo")+ "\t" +
                                    rs.getString("address")+ "\t" +
                                    rs.getInt("nid")+ "\t" +
                                    rs.getInt("submissionNo")
                                  );
            }


        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }



    }

    public void selectAllProducts(){
        String sql = "SELECT * FROM products";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                                    rs.getInt("productID") + "\t" +
                                    rs.getString("name") + "\t" +
                                    rs.getDouble("rate")+ "\t" +
                                    rs.getString("color")+ "\t" +
                                    rs.getString("size")+ "\t" +
                                    rs.getString("rattler")+ "\t" +
                                    rs.getString("notes")
                                  );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void selectAllSubmissions(){
        String sql = "SELECT * FROM submissions";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                                rs.getInt("submissionID") + "\t" +
                                rs.getString("submitTime") + "\t" +
                                rs.getInt("workerID")+ "\t" +
                                rs.getInt("productID")+ "\t" +
                                rs.getInt("productNo")+ "\t" +
                                rs.getString("paid")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


    }

    public void selectAllPayments(){
        String sql = "SELECT * FROM payments";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("paymentID") + "\t" +
                                rs.getString("timeStamp") + "\t" +
                                rs.getInt("workerID")+ "\t" +
                                rs.getString("submissionList")+ "\t" +
                                rs.getInt("totalSubmissions")+ "\t" +
                                rs.getDouble("amountPaid")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


    }

    public void selectEmployeeByName(String eName)
    {
        String sql = "SELECT * FROM workers"+
                     " WHERE name=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {


            ps.setString(1,eName);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("workerID")+ "\t" +
                                rs.getString("name")+ "\t" +
                                rs.getString("phoneNo")+ "\t" +
                                rs.getString("address")+ "\t" +
                                rs.getInt("nid")+ "\t" +
                                rs.getInt("submissionNo")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void selectEmployeeByID(int eID)
    {
        String sql = "SELECT * FROM workers"+
                " WHERE workerID=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {


            ps.setInt(1,eID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("workerID")+ "\t" +
                                rs.getString("name")+ "\t" +
                                rs.getString("phoneNo")+ "\t" +
                                rs.getString("address")+ "\t" +
                                rs.getInt("nid")+ "\t" +
                                rs.getInt("submissionNo")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void selectProductByName(String pName){
        String sql = "SELECT * FROM products"+
                     " WHERE name=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setString(1,pName);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("productID") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getDouble("rate")+ "\t" +
                                rs.getString("color")+ "\t" +
                                rs.getString("size")+ "\t" +
                                rs.getString("rattler")+ "\t" +
                                rs.getString("notes")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void selectProductByID(int pID)
    {
        String sql = "SELECT * FROM products"+
                " WHERE productID=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,pID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("productID") + "\t" +
                                rs.getString("name") + "\t" +
                                rs.getDouble("rate")+ "\t" +
                                rs.getString("color")+ "\t" +
                                rs.getString("size")+ "\t" +
                                rs.getString("rattler")+ "\t" +
                                rs.getString("notes")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getPaymentReportByEmployeeID(int eID)
    {
        String sql = "SELECT * FROM payments"+
                     " WHERE workerID=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("paymentID") + "\t" +
                                rs.getString("timeStamp") + "\t" +
                                rs.getInt("workerID")+ "\t" +
                                rs.getString("submissionList")+ "\t" +
                                rs.getInt("totalSubmissions")+ "\t" +
                                rs.getDouble("amountPaid")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getCurrentSubmissions(int eID)
    {
        String sql = "SELECT * FROM submissions"+
                     " WHERE workerID=? AND paid=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ps.setString(2,"N");
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("submissionID") + "\t" +
                                rs.getString("submitTime") + "\t" +
                                rs.getInt("workerID")+ "\t" +
                                rs.getInt("productID")+ "\t" +
                                rs.getInt("productNo")+ "\t" +
                                rs.getString("paid")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void getSubmissionBySubmissionID(int sID)
    {
        String sql = "SELECT * FROM submissions"+
                " WHERE submissionID=?";

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,sID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("submissionID") + "\t" +
                                rs.getString("submitTime") + "\t" +
                                rs.getInt("workerID")+ "\t" +
                                rs.getInt("productID")+ "\t" +
                                rs.getInt("productNo")+ "\t" +
                                rs.getString("paid")
                );

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void paySubmission(int sID){
        String sql = "UPDATE submissions SET paid = ?"
                + " WHERE submissionID = ?";

        try (Connection conn = this.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // set the corresponding param
            ps.setString(1, "Y");
            ps.setInt(2, sID);
            // update
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCurrentSubmissionForEmployee(int eID,int amount)
    {
        String sql = "UPDATE workers SET submissionNo = ?"
                + " WHERE workerID = ?";

        try(Connection connection = this.connect();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1,amount);
            ps.setInt(2,eID);

            ps.executeUpdate();
        }
        catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
    }


}
