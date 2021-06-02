package database;

import Service.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseController {

    DatabaseQueries dbq = new DatabaseQueries();

    public EmployeeDatabaseController(){

        dbq = new DatabaseQueries();

    }


    public String addNewEmployee(Employee emp) {

        String name = emp.getName();
        String phoneNo = emp.getPhoneNo();
        String address = emp.getAddress();
        int nid = emp.getNid();
        int sub = emp.getCurrentSubmisisons();

        String result = dbq.addEmployee(name,phoneNo, address,nid,sub);
        return result;

    }


    public List<Employee> getAllEmployee() {

        return dbq.selectAllEmployees();


    }

    public List<Employee> getEmployeeByName(String name) {

        List<Employee> employeeList = dbq.selectEmployeeByName(name);
        return employeeList;
    }

    public Employee getEmployeeByID(int id) {

        Employee employee = dbq.selectEmployeeByID(id);
        return employee;

    }

    public void updateEmployeesCurrentSubmission(int eid, int subs)
    {

        dbq.updateCurrentSubmissionForEmployee(eid,subs);

    }




}
