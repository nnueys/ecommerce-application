package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.InputReviewProduct;
import ku.cs.services.ReviewProductFileDataSource;

import java.io.IOException;

public class ReviewProductController {

    @FXML private Button exitButton;
    @FXML private TextField star;
    @FXML private TextArea reviewText;
    @FXML private Label notificationLabel;

    private GetList getList = (GetList) FXRouter.getData();
    private MemberData memberData;
    private ProductData productData;
    private OrderData orderData;
    private OrderDataList orderDataList;
    private InputReviewProduct inputReviewProduct = new InputReviewProduct();
    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;
    private ReviewProductFileDataSource reviewProductFileDataSource;


    @FXML
    public void initialize(){
        this.memberData = getList.getMemberData();
        this.productData = getList.getProductData();
        this.orderData = getList.getOrderData();
        System.out.println("initialize ReviewProduct");

    }


    @FXML
    public void okButton(ActionEvent actionEvent) {

        if (star.getText().equals("")){
            notificationLabel.setText("please rating order");
            return;

        }
        else {
            String usernameCustomer = orderData.getUsernameCustomer();
            String nameProduct = orderData.getNameProduct();
            double stars = Double.parseDouble(star.getText());
            String review = reviewText.getText();
            String shopName = orderData.getShopName();

            System.out.println(stars);
            if(stars > 5 || stars < 0){
                notificationLabel.setText("please enter 1-5");
            } else {
                ReviewProductData reviewProductData = new ReviewProductData(usernameCustomer,nameProduct,shopName,stars,review);
                String reviewProductRecord = inputReviewProduct.reviewProductData(usernameCustomer,nameProduct,shopName,stars,review);
                if(reviewProductRecord.equals("pass")) {
                    try {
                        FXRouter.goTo("market_place");
                    } catch (IOException e) {
                        System.err.println("ไปที่หน้า marketPlace ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกำหนด route");
                    }
                }
            }

        }

    }

    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า orderList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }






}