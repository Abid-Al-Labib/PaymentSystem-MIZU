package fxml;

import Service.Employee;
import Service.Product;
import Service.ProductServiceProvider;
import Service.SubmissionServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class MakeSubmissionController {

        private int workerID;

        private WorkerProfileController workerProfileController;



    @FXML
        private TextField productIDField, quantityField;

        @FXML
        private Button cancelButton;


    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public void setWorkerProfileController(WorkerProfileController workerProfileController) {
        this.workerProfileController = workerProfileController;
    }

    @FXML
    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void submitButtonPressed(ActionEvent actionEvent)
    {

        Product product = null;


        if(quantityField.getText().isEmpty() || productIDField.getText().isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please fill out the fields");

            alert.showAndWait();



        }
        else
        {
            ProductServiceProvider productServiceProvider = new ProductServiceProvider();
            product = productServiceProvider.getProductsByID(Integer.parseInt(this.productIDField.getText()));

            if(product==null)
            {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Product not found");
                alert.setContentText("Please enter valid product ID");

                alert.showAndWait();

            }
            else
            {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Product Found: " + product.productSummary());
                alert.setContentText("Press OK to proceed");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                    SubmissionServiceProvider submissionServiceProvider = new SubmissionServiceProvider();
                    String insert  = submissionServiceProvider.makeSubmission(workerID,Integer.parseInt(productIDField.getText()), Integer.parseInt(quantityField.getText()));

                    int currentSubmission = submissionServiceProvider.calculateTotalSubmissionCount(workerID);

                    workerProfileController.getEmployee().setCurrentSubmisisons(currentSubmission);
                    workerProfileController.getCsLabel().setText(String.valueOf(currentSubmission));

                    Alert alertMessage = new Alert(Alert.AlertType.INFORMATION);
                    alertMessage.setTitle("Information Dialog");
                    alertMessage.setHeaderText(null);
                    alertMessage.setContentText(insert);

                    alertMessage.showAndWait();

                    productIDField.clear();
                    quantityField.clear();

                } else {
                    productIDField.clear();
                    quantityField.clear();
                }

            }



        }



    }

}
