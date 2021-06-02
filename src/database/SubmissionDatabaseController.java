package database;

import Service.Submission;

import java.util.List;

public class SubmissionDatabaseController {

    DatabaseQueries dbq;

    public SubmissionDatabaseController(){

        dbq = new DatabaseQueries();

    }


    public String addSubmissionToDatabase(int workerID, int productID, int productNo)
    {

        String result = dbq.addSubmission(workerID,productID,productNo,"N");
        return result;
    }


    public List<Submission> getCurrentUnpaidSubmissions(int eID)
    {

        List<Submission> submissionList;
        submissionList = dbq.getCurrentSubmissions(eID);
        return submissionList;
    }

    public List<Submission> getAllSubmissionsForThisEmployee(int eID)
    {

        List<Submission> submissionList;
        submissionList = dbq.getSubmissionByEmployeeID(eID);
        return submissionList;

    }

    public Submission getSubmissionBySubmissionID(int sID)
    {
        Submission submission;
        submission = dbq.getSubmissionBySubmissionID(sID);
        return submission;
    }

    public void paySubmission(int sid)
    {
        
        dbq.paySubmission(sid);

    }


    public List<Submission> getUnpaidSubmissionsOfSameProductsForEmployee(int eID, int pID)
    {

        List<Submission> submissionList = dbq.getUnpaidSubmissionsOfSameProductsForEmployee(eID,pID);
        return submissionList;
    }


    public List<Submission> getSubmissionByEmployeeID(int eID) {

        List<Submission> submissionList= dbq.getSubmissionByEmployeeID(eID);
        return submissionList;

    }

    public List<Submission> getSubmissionByDate(int eID, String date)
    {

        List<Submission> submissionList = dbq.getSubmissionByDate(eID, date);
        return submissionList;
    }
}
