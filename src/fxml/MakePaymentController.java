package fxml;

import Service.Employee;
import Service.PaymentServiceProvider;
import Service.SubmissionServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class MakePaymentController {

    private int workerID;
    private WorkerProfileController workerProfile;



    @FXML
    Button cancelButton;

    @FXML
    TextArea textArea;

    @FXML
    Label nameLabel, dateLabel;

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public WorkerProfileController getWorkerProfile() {
        return workerProfile;
    }

    public void setWorkerProfile(WorkerProfileController workerProfile) {
        this.workerProfile = workerProfile;
    }

    @FXML
    public void cancelButtonPressed (ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();

        stage.close();

    }


    @FXML
    public void confirmButtonPressed (ActionEvent actionEvent)
    {

        SubmissionServiceProvider ssp = new SubmissionServiceProvider();
        if(!(ssp.calculateTotalSubmissionCount(this.workerID)==0))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Payment Confirmation");
            alert.setHeaderText("Confirm Payment");
            alert.setContentText("Press OK to complete payment");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){

                PaymentServiceProvider psp = new PaymentServiceProvider(this.workerID);

                String insert = psp.makePayment();
                psp.payAllSubmissions();

                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle("Information Dialog");
                message.setHeaderText(null);
                message.setContentText(""+ insert);

                message.showAndWait();

                int currentSubmissions = ssp.calculateTotalSubmissionCount(this.workerID);
                workerProfile.getEmployee().setCurrentSubmisisons(currentSubmissions);
                workerProfile.getCsLabel().setText(String.valueOf(currentSubmissions));

                textArea.clear();

            } else {


            }

        }


    }

}
