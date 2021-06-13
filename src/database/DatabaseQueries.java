package database;

import Service.Employee;
import Service.Payment;
import Service.Product;
import Service.Submission;

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





    public String addEmployee(String name, String phoneNo, String address, int nid, int submissionNo)
    {
        String sql = "INSERT INTO workers(name,phoneNo,address,nid,submissionNo) VALUES(?,?,?,?,?)";


        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setString(2,phoneNo);
            ps.setString(3,address);
            ps.setInt(4,nid);
            ps.setInt(5,submissionNo);

            //String result = ps.toString();
            ps.executeUpdate();

            return "Successful Insert";

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addProduct(String name, double rate, String color, String size, String rattler, String notes){
        String sql = "INSERT INTO products(name,rate,color,size,rattler,notes) VALUES(?,?,?,?,?,?)";


        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setDouble(2,rate);
            ps.setString(3,color);
            ps.setString(4,size);
            ps.setString(5,rattler);
            ps.setString(6,notes);

            //String result = ps.toString();

            ps.executeUpdate();

            return "Successful Insert";

        } catch (SQLException e) {
            return e.getMessage();
        }
    }


    public String addSubmission(int workerID, int productID, int productNo, String paid){
        String sql = "INSERT INTO submissions(submitTime,workerID,productID,productNo,paid) VALUES(?,?,?,?,?)";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,ts);
            ps.setInt(2,workerID);
            ps.setInt(3,productID);
            ps.setInt(4,productNo);
            ps.setString(5,paid);

            //String result = ps.toString();
            ps.executeUpdate();

            return "Successful Insert";

        } catch (SQLException e) {
            return e.getMessage();
        }
    }


    public String addPayment(int workerID, String submissionList, int totalSubmissions, double amountPaid){
        String sql = "INSERT INTO payments(timestamp,workerID,submissionList,totalSubmissions,amountPaid) VALUES(?,?,?,?,?)";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ts = sdf.format(timestamp);

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,ts);
            ps.setInt(2,workerID);
            ps.setString(3,submissionList);
            ps.setInt(4,totalSubmissions);
            ps.setDouble(5,amountPaid);

            //String result = ps.toString();
            ps.executeUpdate();

            return "Successful Insert";

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<Employee> selectAllEmployees(){
        String sql = "SELECT * FROM workers";
        List<Employee> employeeList = new ArrayList<Employee>();

        try (   Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();



            while(rs.next())
            {

                int id = rs.getInt("workerID");
                String name = rs.getString("name");
                String phone = rs.getString("phoneNo");
                String address = rs.getString("address");
                int nid = rs.getInt("nid");
                int submissionNo = rs.getInt("submissionNo");


                Employee aEmployee = new Employee(id,name,phone,address,nid,submissionNo);
                employeeList.add(aEmployee);

            }



        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return employeeList;

    }

    public List<Product> selectAllProducts(){
        String sql = "SELECT * FROM products";

        List<Product> productList = new ArrayList<Product>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("productID");
                String name = rs.getString("name");
                double rate = rs.getDouble("rate");
                String color = rs.getString("color");
                String size = rs.getString("size");
                String rattler = rs.getString("rattler");
                String notes = rs.getString("notes");

                Product product = new Product(id,name,rate,color,size,rattler,notes);
                productList.add(product);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return productList;

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

    public List<Payment> selectAllPayments(){
        String sql = "SELECT * FROM payments";


        List<Payment> paymentList = new ArrayList<Payment>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int paymentID = rs.getInt("paymentID");
                String timeStamp = rs.getString("timeStamp");
                int workerID = rs.getInt("workerID");
                String submissionList = rs.getString("submissionList");
                int totalSubmissions = rs.getInt("totalSubmissions");
                double amountPaid = rs.getDouble("amountPaid");

                Payment payment = new Payment(paymentID,timeStamp,workerID,submissionList,totalSubmissions,amountPaid);

                paymentList.add(payment);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


        return paymentList;

    }

    public List<Employee> selectEmployeeByName(String eName)
    {
        String sql = "SELECT * FROM workers"+
                     " WHERE name=?";

        List<Employee> employeeList = new ArrayList<Employee>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {


            ps.setString(1,eName);
            ResultSet rs = ps.executeQuery();


            while(rs.next())
            {

                        int id = rs.getInt("workerID");
                        String name = rs.getString("name");
                        String phone = rs.getString("phoneNo");
                        String address = rs.getString("address");
                        int nid = rs.getInt("nid");
                        int submissionNo = rs.getInt("submissionNo");

                        Employee aEmployee = new Employee(id,name,phone,address,nid,submissionNo);
                        employeeList.add(aEmployee);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return employeeList;
    }

    public Employee selectEmployeeByID(int eID)
    {

        String sql = "SELECT * FROM workers"+
                " WHERE workerID=?";

        Employee aEmployee = null;

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {


            ps.setInt(1,eID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("workerID");
                String name = rs.getString("name");
                String phone = rs.getString("phoneNo");
                String address = rs.getString("address");
                int nid = rs.getInt("nid");
                int submissionNo = rs.getInt("submissionNo");

                aEmployee = new Employee(id,name,phone,address,nid,submissionNo);


            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return aEmployee;
    }

    public List<Product> selectProductByName(String pName){
        String sql = "SELECT * FROM products"+
                     " WHERE name=?";

        List<Product> productList = new ArrayList<Product>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setString(1,pName);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                int id = rs.getInt("productID");
                String name = rs.getString("name");
                double rate = rs.getDouble("rate");
                String color = rs.getString("color");
                String size = rs.getString("size");
                String rattler = rs.getString("rattler");
                String notes = rs.getString("notes");

                Product product = new Product(id,name,rate,color,size,rattler,notes);
                productList.add(product);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return productList;
    }

    public Product selectProductByID(int pID)
    {
        String sql = "SELECT * FROM products"+
                " WHERE productID=?";

        Product product = null;

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,pID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("productID");
                String name = rs.getString("name");
                double rate = rs.getDouble("rate");
                String color = rs.getString("color");
                String size = rs.getString("size");
                String rattler = rs.getString("rattler");
                String notes = rs.getString("notes");

                product = new Product(id,name,rate,color,size,rattler,notes);


            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return product;
    }

    public List<Payment> getPaymentByEmployeeID(int eID)
    {
        String sql = "SELECT * FROM payments"+
                     " WHERE workerID=?";

        List<Payment> paymentList = new ArrayList<Payment>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                        int paymentID = rs.getInt("paymentID");
                        String timeStamp = rs.getString("timeStamp");
                        int workerID = rs.getInt("workerID");
                        String submissionList = rs.getString("submissionList");
                        int totalSubmissions = rs.getInt("totalSubmissions");
                        double amountPaid = rs.getDouble("amountPaid");

                        Payment payment = new Payment(paymentID,timeStamp,workerID,submissionList,totalSubmissions,amountPaid);

                        paymentList.add(payment);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return paymentList;
    }

    public List<Submission> getCurrentSubmissions(int eID)
    {
        String sql = "SELECT * FROM submissions" +
                     " INNER JOIN products" +
                     " On submissions.productID = products.productID" +
                     " WHERE workerID=? AND paid=?";

        List<Submission> submissionList = new ArrayList<>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ps.setString(2,"N");
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                        int id = rs.getInt("submissionID");
                        String time = rs.getString("submitTime");
                        int workerID = rs.getInt("workerID");
                        int productID = rs.getInt("productID");
                        int productNo =rs.getInt("productNo");
                        String paid = rs.getString("paid");

                        String productName = rs.getString("name");
                        String size = rs.getString("size");
                        double rate = rs.getDouble("rate");

                        Submission submission = new Submission(id,time,workerID,productID,productNo,paid,productName,size,rate);
                        submissionList.add(submission);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return submissionList;
    }


    public List<Submission> getUnpaidSubmissionsOfSameProductsForEmployee(int eID, int pID)
    {
        String sql = "SELECT * FROM submissions" +
                " INNER JOIN products" +
                " On submissions.productID = products.productID" +
                " WHERE workerID=? AND paid=? AND submissions.productID = ?";

        List<Submission> submissionList = new ArrayList<>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ps.setString(2,"N");
            ps.setInt(3,pID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {

                int id = rs.getInt("submissionID");
                String time = rs.getString("submitTime");
                int workerID = rs.getInt("workerID");
                int productID = rs.getInt("productID");
                int productNo =rs.getInt("productNo");
                String paid = rs.getString("paid");

                String productName = rs.getString("name");
                String size = rs.getString("size");
                double rate = rs.getDouble("rate");

                Submission submission = new Submission(id,time,workerID,productID,productNo,paid,productName,size,rate);
                submissionList.add(submission);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return submissionList;
    }


    public Submission getSubmissionBySubmissionID(int sID)
    {
        String sql = "SELECT * FROM submissions"+
                " INNER JOIN products"+
                " ON submissions.productID = products.productID"+
                " WHERE submissionID=?";

        Submission submission = null;

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,sID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("submissionID");
                String time = rs.getString("submitTime");
                int workerID = rs.getInt("workerID");
                int productID = rs.getInt("productID");
                int productNo =rs.getInt("productNo");
                String paid = rs.getString("paid");

                String productName = rs.getString("name");
                String size = rs.getString("size");
                double rate = rs.getDouble("rate");

                submission = new Submission(id,time,workerID,productID,productNo,paid,productName,size,rate);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return submission;
    }


    public List<Submission> getSubmissionByDate(int eID,String date)
    {
        String sql = "SELECT * FROM submissions"+
                " INNER JOIN products"+
                " ON submissions.productID = products.productID"+
                " WHERE workerID=? AND submitTime = ?";

        List<Submission> submissionList = new ArrayList<>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ps.setString(2,date);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("submissionID");
                String time = rs.getString("submitTime");
                int workerID = rs.getInt("workerID");
                int productID = rs.getInt("productID");
                int productNo =rs.getInt("productNo");
                String paid = rs.getString("paid");

                String productName = rs.getString("name");
                String size = rs.getString("size");
                double rate = rs.getDouble("rate");

                Submission submission = new Submission(id,time,workerID,productID,productNo,paid,productName,size,rate);
                submissionList.add(submission);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return submissionList;
    }


    public List<Submission> getSubmissionByEmployeeID(int eID)
    {
        String sql = "SELECT * FROM submissions"+
                " INNER JOIN products" +
                " On submissions.productID = products.productID"+
                " WHERE workerID = ?";

        List<Submission> submissionList = new ArrayList<>();

        try(    Connection connection = connect();
                PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setInt(1,eID);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int id = rs.getInt("submissionID");
                String time = rs.getString("submitTime");
                int workerID = rs.getInt("workerID");
                int productID = rs.getInt("productID");
                int productNo =rs.getInt("productNo");
                String paid = rs.getString("paid");
                String productName = rs.getString("name");
                String size = rs.getString("size");
                double rate = rs.getDouble("rate");

                Submission submission = new Submission(id,time,workerID,productID,productNo,paid,productName,size,rate);
                submissionList.add(submission);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return submissionList;
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

    public String editProduct(int productID,String name, double rate, String color, String size, String rattler, String notes)
    {
        String sql = "UPDATE products SET name = ?,rate = ?, color = ?, size = ?, rattler = ?, notes = ?"
                + " WHERE productID = ?";

        try(Connection connection = this.connect();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setDouble(2,rate);
            ps.setString(3,color);
            ps.setString(4,size);
            ps.setString(5,rattler);
            ps.setString(6,notes);
            ps.setInt(7,productID);
            ps.executeUpdate();

            return "Changes saved";
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return "Failed";
        }

    }

    public String editEmployee(int workerID,String name, String phoneNo, String address, int nid, int submissionNo)
    {
        String sql = "UPDATE workers SET name = ?, phoneNo = ?, address = ?, nid = ?, submissionNo = ?"
                + " WHERE workerID = ?";

        try(Connection connection = this.connect();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setString(2,phoneNo);
            ps.setString(3,address);
            ps.setInt(4,nid);
            ps.setInt(5,submissionNo);
            ps.setInt(6,workerID);

            ps.executeUpdate();
            return "Changes Saved";
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return "Failed";
        }

    }


}
