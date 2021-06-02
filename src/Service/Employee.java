package Service;

public class Employee {

    private int     employeeID;
    private String  name;
    private String  phoneNo;
    private String  address;
    private int     nid;
    private int     currentSubmisisons;



    public Employee(int employeeID, String name, int submissionNo){
        this.employeeID = employeeID;
        this.name = name;
        this.phoneNo = null;
        this.address = null;
        this.nid = -1;
        this.currentSubmisisons = submissionNo;
    }

    public Employee(String name, String phoneNo, String address, int NID)
    {
        this.employeeID = -1;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.nid = NID;
        this.currentSubmisisons = 0;
    }

    public Employee(int employeeID, String name,String phoneNo, String address, int nid, int submissionNo){
        this.employeeID = employeeID;
        this.name = name;
        this.phoneNo = phoneNo;
        this.address = address;
        this.nid = nid;
        this.currentSubmisisons = submissionNo;
    }



    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getCurrentSubmisisons() {
        return currentSubmisisons;
    }

    public void setCurrentSubmisisons(int currentSubmisisons) {
        this.currentSubmisisons = currentSubmisisons;
    }

}
