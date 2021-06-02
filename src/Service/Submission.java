package Service;

public class Submission {

    private int     submissionID;
    private String  submitTime;
    private int     workerID;
    private int     productID;
    private int     productNo;
    private String  paid;


    private String productName;
    private String productSize;
    private double rate;

    public Submission(int submissionID, String submitTime, int workerID, int productID, int productNo, String paid){

        this.submissionID = submissionID;
        this.submitTime = submitTime;
        this.workerID = workerID;
        this.productID = productID;
        this.productNo = productNo;
        this.paid = paid;
    }




    public Submission(int submissionID, String submitTime, int workerID, int productID, int productNo, String paid, String productName, String productSize, double rate){

        this.submissionID = submissionID;
        this.submitTime = submitTime;
        this.workerID = workerID;
        this.productID = productID;
        this.productNo = productNo;
        this.paid = paid;
        this.productName = productName;
        this.productSize = productSize;
        this.rate = rate;
    }


    public String toStringREPORTFORMAT(){

        String report = "";
        report+= "\nSubmission: \n";
        report+= "WORKER ID: " + this.workerID + "   SUBMISSION DATE: " + this.submitTime + "\n";
        report+= "PRODUCT ID: " + this.productID + " PRODUCT NAME: " + productName +" SIZE: "+productSize + " COUNT: " + productNo + "\n";
        report+= "PAID: " + paid + "\n";
        report+= "-------------------------------------------------------------------------";

        return report;

    }

    public int getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
