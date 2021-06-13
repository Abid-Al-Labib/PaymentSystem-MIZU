package fxml;

import Service.EmployeeServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditWorkerController {

    @FXML
    private Label idLabel,csLabel;
    @FXML
    private TextField nameField,phoneField,addressField,nidField;

    @FXML
    Button cancelButton;

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

    public Label getCsLabel() {
        return csLabel;
    }

    public void setCsLabel(Label csLabel) {
        this.csLabel = csLabel;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public void setPhoneField(TextField phoneField) {
        this.phoneField = phoneField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public void setAddressField(TextField addressField) {
        this.addressField = addressField;
    }

    public TextField getNidField() {
        return nidField;
    }

    public void setNidField(TextField nidField) {
        this.nidField = nidField;
    }

    public void confirmButtonPressed(ActionEvent actionEvent)
    {

        EmployeeServiceProvider es = new EmployeeServiceProvider();


        String name = null;
        String phoneNo;
        String address;
        int nid;


        if(nameField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Alert Message");
            alert.show();
        }
        else {
            name = nameField.getText().trim();
        }


        if(phoneField.getText().trim().isEmpty()){
            phoneNo = "N/A";
        }
        else{
            phoneNo = phoneField.getText().trim();
        }


        if(addressField.getText().trim().isEmpty())
        {
            address = "N/A";
        }
        else{
            address = addressField.getText().trim();
        }


        if(nidField.getText().trim().isEmpty())
        {
            nid = -1;
        }
        else{
            nid = Integer.parseInt(nidField.getText().trim());
        }


        if(name!=null)
        {
            int id = Integer.parseInt(idLabel.getText());
            int cs = Integer.parseInt(csLabel.getText());
            String result = es.editEmployee(id,name, phoneNo, address, nid, cs);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("" + result);
            alert.show();

            nameField.clear();
            addressField.clear();
            phoneField.clear();
            nidField.clear();
            idLabel.setText("");
            csLabel.setText("");
        }



    }

    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();

        stage.close();
    }



}
