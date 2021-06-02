package Service;

import java.util.List;
import java.util.Observable;

public interface EmployeeService {

    public void addWorkerToDatabase(String name,String phoneNo,String Address, int NID);
    public List<Service.Employee> getAllWorkers();

}
