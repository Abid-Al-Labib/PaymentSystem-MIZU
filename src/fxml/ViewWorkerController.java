package fxml;

import Service.Employee;
import Service.EmployeeServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewWorkerController implements Initializable {

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TextField nameField, idField;

    @FXML
    private TableColumn<Employee,String> nameColumn;
    @FXML
    private TableColumn<Employee,Integer> idColumn;
    @FXML
    private TableColumn<Employee,Integer> nidColumn;
    @FXML
    private TableColumn<Employee,String> phoneColumn;
    @FXML
    private Button doneButton;




    @FXML
    public void searchButtonPressed(ActionEvent event)
    {
        List<Employee> employeeList;
        EmployeeServiceProvider esp = new EmployeeServiceProvider();

        System.out.println("you pressed search button");



        if(!(nameField.getText().trim().isEmpty()) || !(idField.getText().trim().isEmpty()))
        {
            if(!nameField.getText().trim().isEmpty())
            {
                employeeList = esp.getWorkerByName(nameField.getText().trim().toString());
            }
            else
            {

                employeeList = new ArrayList<Employee>();
                Employee emp = esp.getWorkerById(Integer.parseInt(idField.getText().trim().toString()));
                if(emp!=null)
                {
                    employeeList.add(emp);
                }
            }

            if(employeeList.size()!=0)
            {
                ObservableList list = FXCollections.observableList(employeeList);
                employeeTable.setItems(list);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Not found");
                alert.setContentText("Please try again with correct inputs!");

                alert.showAndWait();
            }


        }


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
        nidColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("nid"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNo"));

        List<Employee> employeeList;
        EmployeeServiceProvider esp = new EmployeeServiceProvider();
        employeeList = esp.getAllWorkers();

        ObservableList list = FXCollections.observableList(employeeList);

        employeeTable.setItems(list);


    }

    @FXML
    public void doneButtonPresssed(ActionEvent actionEvent) {

        Stage stage = (Stage)doneButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void resetButtonPressed(ActionEvent actionEvent)
    {

        nameField.clear();
        idField.clear();
        List<Employee> employeeList;
        EmployeeServiceProvider esp = new EmployeeServiceProvider();
        employeeList = esp.getAllWorkers();

        ObservableList list = FXCollections.observableList(employeeList);

        employeeTable.setItems(list);

    }

}
