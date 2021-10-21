package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.OrderDataFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class MyPurchaseController {

    @FXML
    private Label nameOrder;

    @FXML
    private Label amountProduct;

    @FXML
    private Label totalPrice;

    @FXML
    private Label tracking;

    @FXML
    private Button exitButton;

    @FXML
    private ListView orderListView;

    @FXML
    private ImageView orderImageView;

    private OrderDataFileDataSource orderDataFileDataSource;
    private OrderData orderData;
    private OrderDataList orderDataList;
    private ArrayList<OrderData> orderDataArrayList;
    private MemberDatalist memberDatalist;
    private ProductDatalist productDatalist;
    private ProductData productData;
    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;
    private GetList getList;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    public void initialize(){

        orderDataFileDataSource = new OrderDataFileDataSource();
        this.orderDataList = orderDataFileDataSource.readData();
        this.orderDataArrayList = orderDataList.getNewOrderProductData();
        showListView();
        clearSelectedMemberCard();
        handleSelectedListView();

    }

    private void showListView(){
        orderListView.getItems().addAll(user());
    }


    private void handleSelectedListView() {

        orderListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<OrderData>() {
                    @Override
                    public void changed(ObservableValue<? extends OrderData> observable,
                                        OrderData oldValue, OrderData newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedOrderData(newValue);
                    }
                });


    }
    private void showSelectedOrderData(OrderData orderData) {

        nameOrder.setText(orderData.getNameProduct());
        amountProduct.setText(String.valueOf(orderData.getOrderProductOrder()));
        totalPrice.setText(String.valueOf(orderData.getTotalPriceOrder()));
        tracking.setText(orderData.getTrackingNumber());
        orderImageView.setImage(new Image("file:"+orderData.getImagePath(),true));
        this.getList = new GetList(memberData,productData,memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);
    }


    private void clearSelectedMemberCard() {
        nameOrder.setText("");
        amountProduct.setText("");
        totalPrice.setText("");
        tracking.setText("");
    }
    public ArrayList<OrderData> user(){
        ArrayList<OrderData> user = new ArrayList<>();
        for (OrderData orderData : orderDataList.getNewOrderProductData() ) {
            if (memberData.getUsername().equals(orderData.getUsernameCustomer())){
                user.add(orderData);
            }
        }
        return user;
    }


    @FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("my_profile_page",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketPlace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void reviewButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("review_product",getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า reviewProduct ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
