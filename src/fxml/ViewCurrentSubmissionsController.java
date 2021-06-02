package fxml;

import Service.*;
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

public class ViewCurrentSubmissionsController implements Initializable {

    private int workerID;
    private String name;
    private int currentSubmission;

    @FXML
    private Label csLabel,nameLabel;
    @FXML
    private Button doneButton;

    @FXML
    private TableView<Submission> submissionTable;
    @FXML
    private TableColumn<Submission,String> timeColumn;
    @FXML
    private TableColumn<Submission,Integer> idColumn;
    @FXML
    private TableColumn<Submission,String>  nameColumn;
    @FXML
    private TableColumn<Submission,String>  sizeColumn;
    @FXML
    private TableColumn<Submission,Integer>  quantityColumn;


    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentSubmission() {
        return currentSubmission;
    }

    public void setCurrentSubmission(int currentSubmission) {
        this.currentSubmission = currentSubmission;
    }

    public Label getCsLabel() {
        return csLabel;
    }

    public void setCsLabel(Label csLabel) {
        this.csLabel = csLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {




        timeColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("submitTime"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Submission, Integer>("productID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("productName"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Submission, String>("productSize"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Submission, Integer>("productNo"));




    }



    public void showDefaultItems(int eID)
    {

        System.out.println(eID);

        List<Submission> submissionList;
        SubmissionServiceProvider submissionServiceProvider = new SubmissionServiceProvider();
        submissionList = submissionServiceProvider.getCurrentSubmissions(eID);
        ObservableList list = FXCollections.observableList(submissionList);

        submissionTable.setItems(list);

    }

    @FXML
    public void doneButtonPressed(ActionEvent actionEvent)
    {
        Stage stage = (Stage)doneButton.getScene().getWindow();
        stage.close();
    }

}
