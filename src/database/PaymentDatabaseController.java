package database;

import Service.Payment;

import java.util.List;

public class PaymentDatabaseController {

    private DatabaseQueries dbq;

    public PaymentDatabaseController()
    {
        dbq = new DatabaseQueries();
    }

    public List<Payment> getPaymentByEmployeeID(int eID)
    {
        List<Payment> paymentList = dbq.getPaymentByEmployeeID(eID);

        return paymentList;
    }

    public List<Payment> selectAllPayments()
    {

        List<Payment> paymentList = dbq.selectAllPayments();

        return paymentList;

    }

    public String addPayment(int workerID, String submissionList, int totalSubmissions, double amountPaid)
    {

        String result = dbq.addPayment(workerID, submissionList, totalSubmissions, amountPaid);
        return result;

    }


}
