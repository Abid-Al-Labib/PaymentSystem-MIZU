package fxml;

import Service.Employee;
import Service.EmployeeServiceProvider;
import Service.Product;
import Service.ProductServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HomepageController{


    @FXML
    public void addEmployeeButtonPressed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddWorker.fxml"));
        Parent root = loader.load();

        AddWorkerController awController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add new Service.Employee");

        stage.show();

    }

    @FXML
    public void viewEmployeeButtonPressed(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewWorker.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Employees");

        stage.show();

    }

    @FXML
    public void addProductButtonPressed(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Employees");

        stage.show();

    }

    @FXML
    public void viewProductButtonPressed(ActionEvent actionEvent) throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewProduct.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("View Employees");

        stage.show();

    }

    @FXML
    public void employeeManagerButtonPressed(ActionEvent actionEvent) throws IOException {

        TextInputDialog dialog = new TextInputDialog("Enter valid ID");
        dialog.setTitle("Employee Manager");
        dialog.setHeaderText("Employee Manager:");
        dialog.setContentText("Please Enter the employee ID:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            EmployeeServiceProvider employeeServiceProvider = new EmployeeServiceProvider();
            int id = Integer.parseInt(result.get());

            Employee employee = employeeServiceProvider.getWorkerById(id);

            if(employee==null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ERROR");
                alert.setHeaderText("Field Empty");
                alert.setContentText("Please enter an ID");

                alert.showAndWait();
            }
            else
            {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Emoployee Manager for: " + employee.getName());
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> button = alert.showAndWait();
                if (button.get() == ButtonType.OK){

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("WorkerProfile.fxml"));
                    Parent root = loader.load();

                    WorkerProfileController workerProfileController = loader.getController();
                    workerProfileController.setEmployee(employee);

                    workerProfileController.getNameLabel().setText(employee.getName());
                    workerProfileController.getIdLabel().setText(""+employee.getEmployeeID());
                    workerProfileController.getPhoneLabel().setText(employee.getPhoneNo());
                    workerProfileController.getAddressLabel().setText(employee.getAddress());
                    workerProfileController.getNidLabel().setText(""+employee.getNid());
                    workerProfileController.getCsLabel().setText(""+employee.getCurrentSubmisisons());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Employee Manager : " + employee.getName());

                    stage.show();

                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }


        }

    }

    public void editEmployeeButtonPressed(ActionEvent actionEvent) throws IOException {

        TextInputDialog dialog = new TextInputDialog("Enter valid ID");
        dialog.setTitle("Edit Employee");
        dialog.setHeaderText("Edit employee:");
        dialog.setContentText("Please Enter the employee ID:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){

            EmployeeServiceProvider employeeServiceProvider = new EmployeeServiceProvider();
            int id = Integer.parseInt(result.get());

            Employee employee = employeeServiceProvider.getWorkerById(id);

            if (employee == null)
            {
                Alert alertBox = new Alert(Alert.AlertType.WARNING);
                alertBox.setTitle("ERROR");
                alertBox.setHeaderText("Field Empty");
                alertBox.setContentText("Please enter an ID");

                alertBox.showAndWait();
            }
            else {

                Alert alertFail = new Alert(Alert.AlertType.CONFIRMATION);
                alertFail.setTitle("Confirmation Dialog");
                alertFail.setHeaderText("Edit " + employee.getName());
                alertFail.setContentText("Are you ok with this?");

                Optional<ButtonType> button = alertFail.showAndWait();
                if (button.get() == ButtonType.OK) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EditWorker.fxml"));
                    Parent root = loader.load();

                    EditWorkerController editWorkerController = loader.getController();


                    editWorkerController.getNameField().setText(employee.getName().toString());
                    editWorkerController.getPhoneField().setText("" + employee.getPhoneNo());
                    editWorkerController.getAddressField().setText(employee.getAddress());
                    editWorkerController.getNidField().setText("" + employee.getNid());
                    editWorkerController.getIdLabel().setText(""+employee.getEmployeeID());
                    editWorkerController.getCsLabel().setText(""+employee.getCurrentSubmisisons());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit : " + employee.getName());

                    stage.show();


                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }


        }



    }
    public void editProductButtonPressed(ActionEvent actionEvent) throws IOException {


            TextInputDialog dialogProduct = new TextInputDialog("Enter valid ID");
            dialogProduct.setTitle("Edit Product");
            dialogProduct.setHeaderText("Edit Product:");
            dialogProduct.setContentText("Please Enter the product ID:");


            Optional<String> response2 = dialogProduct.showAndWait();
            if (response2.isPresent()) {

                ProductServiceProvider productServiceProvider = new ProductServiceProvider();
                int id = Integer.parseInt(response2.get());

                Product product = productServiceProvider.getProductsByID(id);

                if (product == null) {
                    Alert alertFail = new Alert(Alert.AlertType.WARNING);
                    alertFail.setTitle("ERROR");
                    alertFail.setHeaderText("Field Empty");
                    alertFail.setContentText("Please enter an ID");

                    alertFail.showAndWait();
                } else {

                    Alert alertBox = new Alert(Alert.AlertType.CONFIRMATION);
                    alertBox.setTitle("Confirmation Dialog");
                    alertBox.setHeaderText("Edit product : " + product.getName());
                    alertBox.setContentText("Are you ok with this?");

                    Optional<ButtonType> button = alertBox.showAndWait();
                    if (button.get() == ButtonType.OK) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
                        Parent root = loader.load();

                        EditProductController editProductController = loader.getController();

                        editProductController.getNameField().setText(product.getName());
                        editProductController.getColorField().setText(product.getColor());
                        editProductController.getRateField().setText("" + product.getRate());
                        editProductController.getSizeField().setText(product.getSize());
                        editProductController.getIdLabel().setText(""+product.getProductID());
                        if (product.getRattler() == "Y") {
                            editProductController.getRbRattler().setSelected(true);
                        } else {
                            editProductController.getRbRattler().setSelected(false);
                        }
                        editProductController.getNotesField().setText(product.getNotes());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Edit Product: " + product.getName());

                        stage.show();
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                }
            }

    }







}
