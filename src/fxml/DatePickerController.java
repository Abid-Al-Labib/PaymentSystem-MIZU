package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DatePickerController {

    private ViewSubmissionController vsc;



    @FXML
    Button cancelButton;

    @FXML
    TextField dayField, monthField, yearField;

    @FXML
    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();

    }

    public void setVsc(ViewSubmissionController vsc) {
        this.vsc = vsc;
    }

    @FXML
    public void confirmButtonPressed(ActionEvent actionEvent)
    {
        if((dayField.getText().toString().trim().isEmpty())||(monthField.getText().toString().trim().isEmpty())||(yearField.getText().toString().trim().isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incomplete Date");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");

            alert.showAndWait();

        }
        else
        {
            if(dayField.getText().toString().length()>2||monthField.getText().toString().length()>2||yearField.getText().toString().length()>4)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Limit Exceeded");
                alert.setHeaderText(null);
                alert.setContentText("Please correct your input");

                alert.showAndWait();
            }
            else
            {
                if(monthField.getText().toString().length()==2)
                {
                    String date = dayField.getText().toString().trim() + "-" + monthField.getText().toString().trim() + "-" + yearField.getText().toString().trim();
                    vsc.setDateToBeSearched(date);
                    Stage stage = (Stage)cancelButton.getScene().getWindow();
                    stage.close();
                }
                else
                {
                    String date = dayField.getText().toString().trim() + "-" + "0" +monthField.getText().toString().trim() + "-" + yearField.getText().toString().trim();
                    vsc.setDateToBeSearched(date);
                    Stage stage = (Stage)cancelButton.getScene().getWindow();
                    stage.close();
                }

            }


        }


    }

}
