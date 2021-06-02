package fxml;

import Service.Employee;
import Service.EmployeeServiceProvider;
import Service.Product;
import Service.ProductServiceProvider;
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

public class ViewProductController implements Initializable {


    @FXML
    private Button doneButton;

    @FXML
    private TextField nameField, idField;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product,String> nameColumn;

    @FXML
    private TableColumn<Product,Integer> idColumn;

    @FXML
    private TableColumn<Product,Double> rateColumn;

    @FXML
    private TableColumn<Product,String> colorColumn;

    @FXML
    private TableColumn<Product,String> sizeColumn;

    @FXML
    private TableColumn<Product,String> rattlerColumn;

    @FXML
    private TableColumn<Product,String> notesColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("rate"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("color"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("size"));
        rattlerColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("rattler"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("notes"));


        List<Product> productList;
        ProductServiceProvider productServiceProvider = new ProductServiceProvider();
        productList = productServiceProvider.getAllProducts();

        ObservableList list = FXCollections.observableList(productList);

        productTable.setItems(list);

    }

    @FXML
    public void resetButtonPressed(ActionEvent actionEvent){

        nameField.clear();
        idField.clear();
        List<Product> productList;
        ProductServiceProvider productServiceProvider = new ProductServiceProvider();
        productList = productServiceProvider.getAllProducts();

        ObservableList list = FXCollections.observableList(productList);

        productTable.setItems(list);

    }

    @FXML
    public void searchButtonPressed(ActionEvent actionEvent){

        List<Product> productList;
        ProductServiceProvider productServiceProvider = new ProductServiceProvider();

        System.out.println("you pressed search button");



        if(!(nameField.getText().isEmpty()) || !(idField.getText().isEmpty()))
        {
            if(!nameField.getText().isEmpty())
            {
                productList = productServiceProvider.getProductsByName(nameField.getText().toString());
            }
            else
            {
                productList = new ArrayList<Product>();
                Product product;
                product = productServiceProvider.getProductsByID(Integer.parseInt(idField.getText().toString()));

                if(product!=null)
                {
                    productList.add(product);
                }
            }

            if(productList.size()!=0)
            {
                ObservableList list = FXCollections.observableList(productList);
                productTable.setItems(list);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Not found");
                alert.setContentText("Please try again with correct inputs!");

                alert.showAndWait();
            }


        }

    }

    @FXML
    public void doneButtonPressed(ActionEvent actionEvent){

        Stage stage = (Stage)doneButton.getScene().getWindow();
        stage.close();
    }




}
