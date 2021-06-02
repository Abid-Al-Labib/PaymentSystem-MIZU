package database;

import Service.Payment;
import Service.PaymentServiceProvider;
import Service.Submission;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class databaseTester {


    public static void main(String[] args) {
        DatabaseQueries dbq = new DatabaseQueries();

//        dbq.addEmployee("FardinHOE","01736957623","Chittagong",69420,10);
//        dbq.addProduct("puppy", 12.5,"brown","M","Y","cute one");
//        dbq.addSubmission(1,1,20,"Y");
//        dbq.addPayment(2,"2-6-5",60,2000);
//
//        dbq.selectAllEmployees();
//          dbq.selectAllProducts();
//        dbq.selectAllPayments();
//        dbq.selectAllSubmissions();
//
//        dbq.selectEmployeeByName("Abid");
//        dbq.selectEmployeeByID(10);
//        dbq.getPaymentReportByEmployeeID(1);
//        dbq.getCurrentSubmissions(1);
//        dbq.getSubmissionBySubmissionID(3);
//
//        dbq.paySubmission(1);
//        dbq.updateCurrentSubmissionForEmployee(1,0);

//        List<Submission> submissionList= dbq.getSubmissionByEmployeeID(1);
//
//        for(int i = 0; i < submissionList.size(); i++)
//        {
//            System.out.println(submissionList.get(i).toStringREPORTFORMAT());
//        }



//        List<Submission> submissionList = dbq.getUnpaidSubmissionsOfSameProductsForEmployee(12,8);
//
//        for(int i = 0; i< submissionList.size();i++)
//        {
//            System.out.println(submissionList.get(i).toStringREPORTFORMAT());
//
//        }

        /**
         *  When generate payment is clicked it will need to print the report
         *  the report consists of:
         *

         *      -Body
         *          each and every summation detail
         *
         *      -summary
         *          summation breakdown
         *          total number of submissions(current submisison)
         *          total amount to be paid
         */

//        PaymentServiceProvider psp = new PaymentServiceProvider(12);
//
//        //System.out.println(psp.generatePaymentInvoice());
//
//        Payment payment = psp.getLastPaymentForEmployee();
//        System.out.println(psp.generatePaymentReceiptForAPayment(payment));

        List <Submission> submissions = dbq.getSubmissionByDate(12,"29-05-2021");
        for(int i = 0; i < submissions.size();i++)
        {

            System.out.println(submissions.get(i).toStringREPORTFORMAT());

        }



    }

}
