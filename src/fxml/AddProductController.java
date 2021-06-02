package fxml;

import Service.ProductServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class AddProductController {


    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField;
    @FXML
    private TextField rateField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField sizeField;
    @FXML
    private TextArea notesField;

    @FXML
    private RadioButton rbRattler;


    @FXML
    public void confirmButtonPressed(ActionEvent actionEvent)
    {

        String name = null;
        double rate = -1;
        String color;
        String size = null;
        String rattler;
        String notes;

        if(nameField.getText().isEmpty() || rateField.getText().isEmpty() || sizeField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill out name, rate and size field.");
            alert.show();
        }
        else {

            name = nameField.getText().toString();
            rate = Double.parseDouble(rateField.getText().toString());
            size = sizeField.getText().toString();

        }

        if(colorField.getText().isEmpty())
        {
            color = "Color was not specified";
        }
        else
        {
            color = colorField.getText().toString();
        }



        if(rbRattler.isSelected())
        {
            rattler = "Y";
        }
        else
        {
            rattler = "N";
        }

        if(notesField.getText().isEmpty())
        {
            notes = "N/A";
        }
        else
        {
            notes = notesField.getText().toString();
        }

        if(name!=null && size!=null && rate!= -1)
        {
            ProductServiceProvider productServiceProvider = new ProductServiceProvider();
            String result = productServiceProvider.addProductToDatabase(name,rate,color,size,rattler,notes);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("" + result);
            alert.show();

            nameField.clear();
            rateField.clear();
            colorField.clear();
            sizeField.clear();
            notesField.clear();
            rbRattler.setSelected(false);
        }


    }

    @FXML
    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();

        stage.close();

    }


}
