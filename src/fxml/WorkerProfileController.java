package fxml;

import Service.Employee;
import Service.EmployeeServiceProvider;
import Service.PaymentServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class WorkerProfileController {

    @FXML
    private Label nameLabel, idLabel,addressLabel, phoneLabel, nidLabel, csLabel;

    @FXML
    private Button doneButton;



    Employee employee;



    public void setEmployee(Employee employee) {

        this.employee = employee;

    }

    public Employee getEmployee() {

        return this.employee;

    }


    public Label getNameLabel() {
        return nameLabel;
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public Label getAddressLabel() {
        return addressLabel;
    }

    public Label getPhoneLabel() {
        return phoneLabel;
    }

    public Label getNidLabel() {
        return nidLabel;
    }

    public Label getCsLabel() {
        return csLabel;
    }


    @FXML
    public void newSubmissionButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeSubmission.fxml"));
        Parent root = loader.load();

        MakeSubmissionController makeSubmissionController = loader.getController();

        makeSubmissionController.setWorkerID(this.employee.getEmployeeID());
        makeSubmissionController.setWorkerProfileController(this);



        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Make New Submissions for : " + employee.getName());

        stage.show();

    }

    @FXML
    public void viewSubmissionButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSubmission.fxml"));
        Parent root = loader.load();

        ViewSubmissionController ViewSubmissionController = loader.getController();
        ViewSubmissionController.setNameLabel(this.employee.getName());
        ViewSubmissionController.setEID(this.employee.getEmployeeID());

        ViewSubmissionController.showDefaultItems(this.employee.getEmployeeID());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Submissions for : " + employee.getName());

        stage.show();


    }

    @FXML
    public void makePaymentButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MakePayment.fxml"));
        Parent root = loader.load();

        MakePaymentController makePaymentController = loader.getController();
        makePaymentController.getNameLabel().setText(this.employee.getName());
        makePaymentController.setWorkerID(this.employee.getEmployeeID());
        makePaymentController.setWorkerProfile(this);

        PaymentServiceProvider psp = new PaymentServiceProvider(employee.getEmployeeID());
        makePaymentController.getTextArea().appendText(psp.generatePaymentInvoice());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        makePaymentController.getDateLabel().setText(now.toString().substring(0,10));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Make Payment for: " + employee.getName());

        stage.show();

    }

    @FXML
    public void paymentReportButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentReport.fxml"));
        Parent root = loader.load();

        PaymentReportController paymentReportController = loader.getController();
        paymentReportController.setPaymentServiceProvider(new PaymentServiceProvider(employee.getEmployeeID()));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View payments report for : " + employee.getName());

        stage.show();

    }

    @FXML
    public void viewCurrentSubmissionButtonPressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCurrentSubmissions.fxml"));
        Parent root = loader.load();

        ViewCurrentSubmissionsController viewCurrentSubmissionsController = loader.getController();
        viewCurrentSubmissionsController.setName(this.employee.getName());
        viewCurrentSubmissionsController.setWorkerID(this.employee.getEmployeeID());

        viewCurrentSubmissionsController.setCurrentSubmission(this.employee.getCurrentSubmisisons());
        viewCurrentSubmissionsController.setName(this.employee.getName());
        viewCurrentSubmissionsController.setWorkerID(this.employee.getEmployeeID());
        viewCurrentSubmissionsController.getNameLabel().setText(this.employee.getName());
        viewCurrentSubmissionsController.getCsLabel().setText(String.valueOf(this.employee.getCurrentSubmisisons()));

        viewCurrentSubmissionsController.showDefaultItems(this.employee.getEmployeeID());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Current Submissions for : " + employee.getName());

        stage.show();


    }

    @FXML
    public void doneButtonPressed(ActionEvent actionEvent)
    {
        EmployeeServiceProvider esp = new EmployeeServiceProvider();
        esp.updateCurrentSubmissionForEmployee(this.employee.getEmployeeID(),this.employee.getCurrentSubmisisons());

        Stage stage = (Stage)doneButton.getScene().getWindow();
        stage.close();

    }



}
