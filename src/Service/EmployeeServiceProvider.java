package Service;

import database.EmployeeDatabaseController;

import java.util.List;

public class EmployeeServiceProvider{

    EmployeeDatabaseController employeeDatabaseController;

    public EmployeeServiceProvider(){
        employeeDatabaseController = new EmployeeDatabaseController();

    }


    public String addWorkerToDatabase(String name,String phoneNo,String Address, int NID) {


        Employee emp = new Employee(name,phoneNo,Address,NID);

        String result = employeeDatabaseController.addNewEmployee(emp);
        return result;

    }


    public List<Employee> getAllWorkers() {

        List<Employee> employeeList = employeeDatabaseController.getAllEmployee();

        return employeeList;

    }


    public List<Employee> getWorkerByName(String name) {

        List<Employee> employeeList = employeeDatabaseController.getEmployeeByName(name);
        return employeeList;
    }

    public Employee getWorkerById(int id) {

         Employee employee = employeeDatabaseController.getEmployeeByID(id);
        return employee;

    }

    public void updateCurrentSubmissionForEmployee(int eid, int subs)
    {

        employeeDatabaseController.updateEmployeesCurrentSubmission(eid, subs);

    }

    public String editEmployee(int workerID,String name, String phoneNo, String address, int nid, int submissionNo)
    {
        String result = employeeDatabaseController.editEmployee(workerID,name,phoneNo,address,nid,submissionNo);
        return result;
    }

}
