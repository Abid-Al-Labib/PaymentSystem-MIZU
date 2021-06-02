package fxml;

import Service.Payment;
import Service.PaymentServiceProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.List;


public class PaymentReportController {

    PaymentServiceProvider paymentServiceProvider;

    public void setPaymentServiceProvider(PaymentServiceProvider psp)
    {
        this.paymentServiceProvider = psp;
    }

    @FXML
    Button done;

    @FXML
    TextArea textArea;


    @FXML
    public void showAllPaymentButtonPressed(ActionEvent actionEvent)
    {

        List<Payment> paymentList = paymentServiceProvider.getAllPaymentForEmployee();
        if(paymentList.size()!=0)
        {

            for(int i =0; i<paymentList.size();i++)
            {

                String receipt = paymentServiceProvider.generatePaymentReceiptForAPayment(paymentList.get(i));
                textArea.appendText(receipt);

            }

        }
        else
        {
            textArea.setText("No previous payments for this employee");
        }


    }

    @FXML
    public void showLastPaymentButtonPressed(ActionEvent actionEvent)
    {

        Payment payment = paymentServiceProvider.getLastPaymentForEmployee();

        if(payment!=null)
        {
            String receipt = paymentServiceProvider.generatePaymentReceiptForAPayment(payment);
            textArea.appendText(receipt);
        }
        else
        {
            textArea.setText("No previous paymount for this employee");
        }

    }

    @FXML
    public void doneButtonPressed(ActionEvent actionEvent)
    {

        Stage stage = (Stage)done.getScene().getWindow();

        stage.close();

    }


}
