package fxml;

import Service.ProductServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class EditProductController {

    @FXML
    private Label idLabel;

    public Label getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Label idLabel) {
        this.idLabel = idLabel;
    }

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


    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getRateField() {
        return rateField;
    }

    public void setRateField(TextField rateField) {
        this.rateField = rateField;
    }

    public TextField getColorField() {
        return colorField;
    }

    public void setColorField(TextField colorField) {
        this.colorField = colorField;
    }

    public TextField getSizeField() {
        return sizeField;
    }

    public void setSizeField(TextField sizeField) {
        this.sizeField = sizeField;
    }

    public TextArea getNotesField() {
        return notesField;
    }

    public void setNotesField(TextArea notesField) {
        this.notesField = notesField;
    }

    public RadioButton getRbRattler() {
        return rbRattler;
    }

    public void setRbRattler(RadioButton rbRattler) {
        this.rbRattler = rbRattler;
    }

    @FXML
    public void confirmButtonPressed(ActionEvent actionEvent)
    {

        String name = null;
        double rate = -1;
        String color;
        String size = null;
        String rattler;
        String notes;

        if(nameField.getText().trim().isEmpty() || rateField.getText().trim().isEmpty() || sizeField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill out name, rate and size field.");
            alert.show();
        }
        else {

            name = nameField.getText().trim().toString();
            rate = Double.parseDouble(rateField.getText().trim().toString());
            size = sizeField.getText().trim().toString();

        }

        if(colorField.getText().trim().isEmpty())
        {
            color = "Color was not specified";
        }
        else
        {
            color = colorField.getText().trim().toString();
        }



        if(rbRattler.isSelected())
        {
            rattler = "Y";
        }
        else
        {
            rattler = "N";
        }

        if(notesField.getText().trim().isEmpty())
        {
            notes = "N/A";
        }
        else
        {
            notes = notesField.getText().trim().toString();
        }

        if(name!=null && size!=null && rate!= -1)
        {
            int id = Integer.parseInt(idLabel.getText());
            ProductServiceProvider productServiceProvider = new ProductServiceProvider();
            String result = productServiceProvider.editProduct(id,name,rate,color,size,rattler,notes);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("" + result);
            alert.show();

            nameField.clear();
            rateField.clear();
            colorField.clear();
            sizeField.clear();
            notesField.clear();
            rbRattler.setSelected(false);
            idLabel.setText("");
        }


    }

    @FXML
    public void cancelButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)cancelButton.getScene().getWindow();

        stage.close();

    }


}
