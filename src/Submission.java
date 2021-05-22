public class Submission {

    private int     submissionID;
    private String  submitTime;
    private int     workerID;
    private int     productID;
    private int     productNo;
    private String  paid;


    public Submission(int submissionID, String submitTime, int workerID, int productID, int productNo, String paid){

        this.submissionID = submissionID;
        this.submitTime = submitTime;
        this.workerID = workerID;
        this.productID = productID;
        this.productNo = productNo;
        this.paid = paid;
    }

    public void makeSubmissionPaid(){

        //use sql query to change to paid

    }

    public String toStringREPORTFORMAT(){

        return null;

    }

}
