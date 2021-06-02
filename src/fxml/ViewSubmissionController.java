package fxml;

import Service.Submission;
import Service.SubmissionServiceProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSubmissionController implements Initializable {

    private String dateToBeSearched;
    private int eID;

    @FXML
    Label nameLabel;

    @FXML
    Button doneButton;

    @FXML
    TableColumn<Submission, String> timeColumn;
    @FXML
    TableColumn<Submission, Integer> productIDcolumn;
    @FXML
    TableColumn<Submission, String> productNameColumn;
    @FXML
    TableColumn<Submission, String> sizeColumn;
    @FXML
    TableColumn<Submission, String> paidColumn;
    @FXML
    TableColumn<Submission, Integer> quantityColumn;

    @FXML
    TableView <Submission> table;

    @FXML
    public void doneButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)doneButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void searchByDateButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DatePicker.fxml"));
        Parent root = loader.load();

        DatePickerController datePickerController = loader.getController();

        datePickerController.setVsc(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Date Picker ");

        stage.showAndWait();

        System.out.println(dateToBeSearched);
        List<Submission> submissionList;
        SubmissionServiceProvider submissionServiceProvider = new SubmissionServiceProvider();
        submissionList = submissionServiceProvider.getSubmissionByDate(eID,dateToBeSearched);
        ObservableList list = FXCollections.observableList(submissionList);

        table.setItems(list);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("submitTime"));
        productIDcolumn.setCellValueFactory(new PropertyValueFactory<Submission, Integer>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("productName"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("productSize"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Submission, Integer>("productNo"));
        paidColumn.setCellValueFactory(new PropertyValueFactory<Submission,String>("paid"));

    }

    public void showDefaultItems(int eID)
    {

        List<Submission> submissionList;
        SubmissionServiceProvider submissionServiceProvider = new SubmissionServiceProvider();
        submissionList = submissionServiceProvider.getSubmissionByEmployeeID(eID);
        ObservableList list = FXCollections.observableList(submissionList);

        table.setItems(list);

    }



    public void setDateToBeSearched(String date)
    {
        this.dateToBeSearched = date;
    }

    public void setEID(int eID)
    {
        this.eID = eID;
    }

    public void setNameLabel(String name) {
        this.nameLabel.setText(name);
    }
}
