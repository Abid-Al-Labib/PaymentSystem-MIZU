package Service;

import java.util.ArrayList;

public class Payment {

     private int                    paymentID;
     private String                 timeStamp;
     private int                    workerID;
     private String                 submissionList;
     private int                    totalSubmissions;
     private double                 amountPaid;


     public Payment(int paymentID, String timeStamp, int workerID, String submissionList, int totalSubmissions, double amountPaid){

         this.paymentID = paymentID;
         this.timeStamp = timeStamp;
         this.workerID = workerID;
         this.submissionList = submissionList;
         this.totalSubmissions = totalSubmissions;
         this.amountPaid = amountPaid;

     }

     public String toStringReportFormat()
     {

         String report = "_______________________ PAYMENT RECEIPT _______________________";
         report+= "Worker ID: " + this.workerID + " TimeStamp: " + this.timeStamp + "\n";
         report+= "totalSubmissions : " + this.totalSubmissions + "submission List : " + this.submissionList + "\n";
         report+= "TOTAL PAID: " + this.amountPaid;
         report+= "\n submission breakdown: \n";

         return report;

     }





    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public String getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(String submissionList) {
        this.submissionList = submissionList;
    }

    public int getTotalSubmissions() {
        return totalSubmissions;
    }

    public void setTotalSubmissions(int totalSubmissions) {
        this.totalSubmissions = totalSubmissions;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
