package Service;

import database.SubmissionDatabaseController;

import java.util.List;

public class SubmissionServiceProvider {

    private SubmissionDatabaseController submissionDatabaseController;

    public SubmissionServiceProvider()
    {
        submissionDatabaseController = new SubmissionDatabaseController();
    }


    public List<Submission> getCurrentSubmissions(int workerID) {

        List<Submission> submissionList= submissionDatabaseController.getCurrentUnpaidSubmissions(workerID);
        return submissionList;
    }

    public String makeSubmission(int workerID, int productID, int quantity) {

        String result = submissionDatabaseController.addSubmissionToDatabase(workerID, productID, quantity);
        return result;

    }

    public void paySubmission(int sid)
    {

        submissionDatabaseController.paySubmission(sid);
    }

    public List<Submission> getUnpaidSubmissionsOfSameProductsForEmployee(int eID, int pID)
    {

        List<Submission> submissionList = submissionDatabaseController.getUnpaidSubmissionsOfSameProductsForEmployee(eID,pID);
        return submissionList;

    }

    public int caclulateNumberofSubmissionsForProductID(int workerID,int productID)
    {
        int total = 0;

        List<Submission> submissionList = getUnpaidSubmissionsOfSameProductsForEmployee(workerID, productID);
        for(int i = 0; i < submissionList.size();i++)
        {

            total+= submissionList.get(i).getProductNo();

        }

        return total;
    }

    public int caclulateNumberofSubmissionsForProductID(List<Submission> submissionList,int productID)
    {
        int total = 0;

        for(int i = 0; i < submissionList.size();i++)
        {
            if(submissionList.get(i).getProductID()==productID)
            {
                total+= submissionList.get(i).getProductNo();
            }
        }

        return total;
    }


    public int calculateTotalSubmissionCount(List<Submission> submissionList) {

        int totalSubmissions = 0;

        for(int i = 0; i< submissionList.size();i++)
        {

            totalSubmissions+= submissionList.get(i).getProductNo();

        }

        return  totalSubmissions;
    }

    public int calculateTotalSubmissionCount(int workerID) {

        int totalSubmissions = 0;
        List<Submission> submissionList = getCurrentSubmissions(workerID);
        for(int i = 0; i< submissionList.size();i++)
        {

            totalSubmissions+= submissionList.get(i).getProductNo();

        }

        return  totalSubmissions;
    }


    public Submission getSubmissionBySubmissionID(int sid)
    {

        Submission submission = submissionDatabaseController.getSubmissionBySubmissionID(sid);
        return submission;
    }

    public List<Submission> getSubmissionByEmployeeID(int eID) {

       List<Submission> submissionList = submissionDatabaseController.getSubmissionByEmployeeID(eID);

       return submissionList;

    }

    public List<Submission> getSubmissionByDate(int eID, String dateToBeSearched) {

        List<Submission> submissionList = submissionDatabaseController.getSubmissionByDate(eID, dateToBeSearched);
        return submissionList;

    }
}
