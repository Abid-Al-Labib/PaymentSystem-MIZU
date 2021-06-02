package Service;

import database.PaymentDatabaseController;

import java.util.*;

public class PaymentServiceProvider {

    PaymentDatabaseController paymentDatabaseController;
    ProductServiceProvider productServiceProvider;
    SubmissionServiceProvider submissionServiceProvider;

    int workerID;


    public PaymentServiceProvider(int workerID)
    {
        paymentDatabaseController = new PaymentDatabaseController();
        submissionServiceProvider = new SubmissionServiceProvider();
        productServiceProvider = new ProductServiceProvider();

        this.workerID = workerID;
    }


    public String[] submissionListParser(String submissionList)
    {
        String [] result = submissionList.split("-");
        return result;
    }

    public String submissionListCreator()
    {
        List<Submission> submissions = submissionServiceProvider.getCurrentSubmissions(this.workerID);

        String result = "";
        if(submissions.size()==1)
        {

            result+= submissions.get(0).getSubmissionID();
        }
        else
        {
            result+= submissions.get(0).getSubmissionID();
            int i = 1;
            while(i < submissions.size()) {
                result += "-";
                result += submissions.get(i).getSubmissionID();
                i++;
            }
            return result;

        }

        return result;


    }

    public double calculatePayment(List<Submission> submissions)
    {

        double total = 0;
        for(int i = 0; i<submissions.size();i++)
        {

            total += (submissions.get(i).getProductNo() * submissions.get(i).getRate());

        }
        return total;

    }




    public void payAllSubmissions()
    {

        List<Submission> submissionList = submissionServiceProvider.getCurrentSubmissions(this.workerID);

        for(int i = 0; i<submissionList.size();i++)
        {

            submissionServiceProvider.paySubmission(submissionList.get(i).getSubmissionID());

        }

    }

    public List<Payment> getAllPaymentForEmployee()
    {
        List<Payment> paymentList= paymentDatabaseController.getPaymentByEmployeeID(this.workerID);
        return paymentList;
    }


    public Payment getLastPaymentForEmployee()
    {

        List<Payment> paymentList= paymentDatabaseController.getPaymentByEmployeeID(this.workerID);
        if(paymentList.size()!=0)
        {
            Payment payment =  paymentList.get(paymentList.size()-1);

            return payment;
        }
        else
        {
            return null;
        }
    }

    public String breakDownTotalSubmission( )
    {
        List<Submission> submissionList = submissionServiceProvider.getCurrentSubmissions(this.workerID);
        Set<Integer> set = new HashSet<Integer>();

        for(int i = 0; i < submissionList.size(); i++){
            set.add(submissionList.get(i).getProductID());
        }

        Integer [] uniqueSubmissions = set.toArray(new Integer[0]);
        String breakdown = "";
        for (int i = 0; i< uniqueSubmissions.length;i++)
        {
            Product product = productServiceProvider.getProductsByID(uniqueSubmissions[i]);
            int countOfProduct = submissionServiceProvider.caclulateNumberofSubmissionsForProductID(this.workerID,uniqueSubmissions[i]);
            breakdown+= "PRODUCT ID: " + product.getProductID() + "  PRODUCT NAME: "+
                         product.getName() + "  RATE: " +product.getRate() +"  SIZE: "+product.getSize()+ "  COUNT: " + countOfProduct + "\n";

        }

        return breakdown;
//        Iterator it = set.iterator();
//        while(it.hasNext()) {
//            System.out.println(it.next());
//        }

    }

    public String breakDownTotalSubmission(List<Submission> submissionList)
    {
        Set<Integer> set = new HashSet<Integer>();

        for(int i = 0; i < submissionList.size(); i++){
            set.add(submissionList.get(i).getProductID());
        }

        Integer [] uniqueSubmissions = set.toArray(new Integer[0]);
        String breakdown = "";
        for (int i = 0; i< uniqueSubmissions.length;i++)
        {
            Product product = productServiceProvider.getProductsByID(uniqueSubmissions[i]);
            int countOfProduct = submissionServiceProvider.caclulateNumberofSubmissionsForProductID(submissionList,uniqueSubmissions[i]);
            breakdown+= "PRODUCT ID: " + product.getProductID() + "  PRODUCT NAME: "+
                    product.getName() + "  RATE: " +product.getRate() +"  SIZE: "+product.getSize()+ "  COUNT: " + countOfProduct + "\n";

        }

        return breakdown;


    }


    public String generatePaymentInvoice()
    {

        if(submissionServiceProvider.calculateTotalSubmissionCount(submissionServiceProvider.getCurrentSubmissions(workerID))==0)
        {
            return "No current submissions to pay";

        }

        List<Submission> currentSubmissionList = submissionServiceProvider.getCurrentSubmissions(this.workerID);




        String invoice = "******************** PAYMENT INVOICE *****************\n";
        invoice += "SUBMISSION BREAKDOWN: \n";

        for(int i = 0; i< currentSubmissionList.size();i++)
        {

            invoice+= currentSubmissionList.get(i).toStringREPORTFORMAT();

        }

        invoice += "\n                       SUMMARY \n";
        invoice += breakDownTotalSubmission();

        invoice += "\nTOTAL NUMBER OF SUBMISSIONS: " + submissionServiceProvider.calculateTotalSubmissionCount(this.workerID);
        invoice += "\nTOTAL COST: " + calculatePayment(currentSubmissionList);

        return invoice;

    }

    public String generatePaymentReceiptForAPayment(Payment payment)
    {


        String [] submissionList = submissionListParser(payment.getSubmissionList());
        List <Submission> submissions = new ArrayList<>();

        for (int i  = 0 ; i<submissionList.length;i++)
        {
            submissions.add(submissionServiceProvider.getSubmissionBySubmissionID(Integer.parseInt(submissionList[i])));
        }


        String invoice = "******************** PAYMENT RECIEPT *****************\n";
        invoice += "DATE OF PAYMENT: " + payment.getTimeStamp();
        invoice += "\nSUBMISSION BREAKDOWN: \n";

        for(int i = 0; i< submissions.size();i++)
        {

            invoice+= submissions.get(i).toStringREPORTFORMAT();

        }

        invoice += "\n                       SUMMARY \n";
        invoice += breakDownTotalSubmission(submissions);

        invoice += "\nTOTAL NUMBER OF SUBMISSIONS: " + submissionServiceProvider.calculateTotalSubmissionCount(submissions);
        invoice += "\nTOTAL COST: " + calculatePayment(submissions) + "\n\n";

        return invoice;

    }


    public String makePayment() {

        String submissionList = submissionListCreator();
        int totalSubmission = submissionServiceProvider.calculateTotalSubmissionCount(this.workerID);
        double amountPaid = calculatePayment(submissionServiceProvider.getCurrentSubmissions(this.workerID));
        String result = paymentDatabaseController.addPayment(this.workerID,submissionList,totalSubmission,amountPaid);

        return result;
    }
}
