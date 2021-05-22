public class Employee {

    private int     employeeID;
    private String  name;
    private String  phoneNo;
    private String  address;
    private int     nid;
    private int     currentSubmisisons;


    public Employee(int employeeID, String name, int submissionNo){
        this.employeeID = employeeID;
        this.name = name;
        this.phoneNo = null;
        this.address = null;
        this.nid = -1;
        this.currentSubmisisons = submissionNo;
    }

    public Employee(int employeeID, String name,String phoneNo, String address, int nid, int submissionNo){
        this.employeeID = employeeID;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.nid = nid;
        this.currentSubmisisons = submissionNo;
    }

    public void makePayment(){

        //Update database with currentSubmission as 0 and
        //Update all unpaid submissions to paid
        //create new payment

    }

    public void showPayment(){

        //calculate payment for unpaid submissions

    }

   private void getSubmissionListForUnpaidSubmissions(){

        //parse submission list
       //format: submissionID-submissionID-submisionID

   }

    public void newSubmission(int submissions){

        //add new submission to the database
        //increase currentSubmissions by upadating the database

    }

    public void addThisEmployeeToDatabase(){

        //create a new employee in the database

    }

    public void paymentReport(){

        //use Payment to get all payments of this employee
        //produce payment report.

    }

}
