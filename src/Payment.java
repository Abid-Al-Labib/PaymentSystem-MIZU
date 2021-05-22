import java.util.ArrayList;

public class Payment {

     private int                    paymentID;
     private String                 timeStamp;
     private int                    workerID;
     private ArrayList<Submission>  submissionList;
     private int                    totalSubmissions;
     private double                 amountPaid;


     public Payment(int paymentID, String timeStamp, int workerID, String submissionList, int totalSubmissions, double amountPaid){

         this.paymentID = paymentID;
         this.timeStamp = timeStamp;
         this.workerID = workerID;
         this.submissionList = new ArrayList<>();
         this.totalSubmissions = totalSubmissions;
         this.amountPaid = amountPaid;

         populateList(submissionList);

     }


    public void paymentReport(){



    }

    public void addPaymentToDatabase(){



    }


     private void populateList(String submissionLIst){

         //helper function to populate the submissionList

     }

     public ArrayList<Submission> getSubmissionList(){
         return submissionList;
     }


}
