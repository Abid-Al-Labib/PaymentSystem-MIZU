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
        if((dayField.getText().trim().isEmpty())||(monthField.getText().trim().isEmpty())||(yearField.getText().trim().isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incomplete Date");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");

            alert.showAndWait();

        }
        else
        {
            if(dayField.getText().trim().length()>2||monthField.getText().trim().length()>2||yearField.getText().trim().length()>4)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Limit Exceeded");
                alert.setHeaderText(null);
                alert.setContentText("Please correct your input");

                alert.showAndWait();
            }
            else
            {
                if(monthField.getText().trim().length()==2)
                {
                    String date = dayField.getText().trim() + "-" + monthField.getText().trim() + "-" + yearField.getText().trim();
                    vsc.setDateToBeSearched(date);
                    Stage stage = (Stage)cancelButton.getScene().getWindow();
                    stage.close();
                }
                else
                {
                    String date = dayField.getText().trim() + "-" + "0" +monthField.getText().trim() + "-" + yearField.getText().trim();
                    vsc.setDateToBeSearched(date);
                    Stage stage = (Stage)cancelButton.getScene().getWindow();
                    stage.close();
                }

            }


        }


    }

}
