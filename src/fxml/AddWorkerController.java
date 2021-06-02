package fxml;

import Service.EmployeeServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddWorkerController {

    @FXML
    TextField nameField,phoneField,addressField,nidField;

    @FXML
    Button cancelButton;

    public void confirmButtonPressed(ActionEvent actionEvent)
    {

        EmployeeServiceProvider es = new EmployeeServiceProvider();


        String name = null;
        String phoneNo;
        String address;
        int nid;


        if(nameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Alert Message");
            alert.show();
        }
        else {
            name = nameField.getText();
        }


        if(phoneField.getText().isEmpty()){
            phoneNo = "N/A";
        }
        else{
            phoneNo = phoneField.getText();
        }


        if(addressField.getText().isEmpty())
        {
            address = "N/A";
        }
        else{
            address = addressField.getText();
        }


        if(nidField.getText().isEmpty())
        {
            nid = -1;
        }
        else{
            nid = Integer.parseInt(nidField.getText());
        }


        if(name!=null)
        {
            String result = es.addWorkerToDatabase(name, phoneNo, address, nid);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("" + result);
            alert.show();

            nameField.clear();
            addressField.clear();
            phoneField.clear();
            nidField.clear();
        }



    }

    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();

        stage.close();
    }



}
