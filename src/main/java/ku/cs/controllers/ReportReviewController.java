package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.InputReport;

import java.io.IOException;

public class ReportReviewController {
    @FXML
    private Button exitButton;

    @FXML
    private TextField reportReviewText;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Label notificationLabel;

    private GetList getList = (GetList) FXRouter.getData();
    private MemberData memberData;
    private ProductData productData;
    private ReportProductData reportProductData;
    private ReviewProductData reviewProductData;
    private InputReport inputReport = new InputReport();


    public void initialize(){
        memberData = getList.getMemberData();
        productData = getList.getProductData();
        reviewProductData = getList.getReviewProductData();
        comboBox.getItems().addAll("review is include abusive content","review is not true","review is hateful");
    }

    public void getReportComboBox(){
        if (reportReviewText.getText().equals("")){
            notificationLabel.setText("if nothing to enter more description please enter - ");
            return;

        } else {
            String reportType = this.comboBox.getValue().toString();
            String usernameReview = reviewProductData.getUsername();
            String usernameCustomer = memberData.getUsername();
            String product = reviewProductData.getNameProduct();
            String reportText = reportReviewText.getText();


            ReportProductData reportProductData = new ReportProductData(usernameReview, usernameCustomer, product, reportType, reportText);

            String recordReportProduct = inputReport.reportProductData(usernameReview, usernameCustomer, product, reportType, reportText);
            if (recordReportProduct.equals("pass")) {
                try {
                    FXRouter.goTo("order");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า order ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
            }
        }
    }

    @FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
