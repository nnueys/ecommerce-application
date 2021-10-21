package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.OrderData;
import ku.cs.models.OrderDataList;
import ku.cs.models.ProductData;
import ku.cs.services.DataSource;
import ku.cs.services.OrderDataFileDataSource;

import java.io.IOException;

public class TrackingNumberController {
    @FXML private TextField trackingNumberTextField;
    @FXML private Button exitButton;

    private DataSource<OrderDataList> dataSource;
    private OrderDataList orderDataList;
    private OrderData orderData = (OrderData) FXRouter.getData();
    private MemberData memberData;
    private ProductData productData;

    @FXML
    private void initialize() {
        System.out.println("initialize TrackingNumberController");
        dataSource = new OrderDataFileDataSource("Order","Order.csv");
        orderDataList = dataSource.readData();
    }

    @FXML
    public void handleUseConfirmTrackingButton(ActionEvent actionEvent) {
        String usernameCustomer = orderData.getUsernameCustomer();
        String usernameShop = orderData.getShopName();
        String nameOrderProduct = orderData.getNameProduct();
        double priceOrderProduct = Double.parseDouble(String.valueOf(orderData.getPriceProduct()));
        int AmountOrderProduct = Integer.parseInt(String.valueOf(orderData.getOrderProductOrder()));
        String imagePath = orderData.getImagePath();
        String trackingOrder = orderData.getTrackingNumber();
        String newTrackingOrder = trackingNumberTextField.getText();

        DataSource<OrderDataList> dataSource;
        String directory = "Order";
        String filename = "Order.csv";

        dataSource = new OrderDataFileDataSource(directory, filename);
        OrderDataList orderDataList = dataSource.readData();

        String editTrackingNumber = orderDataList.recordTrackingNumber(usernameCustomer, usernameShop, nameOrderProduct, priceOrderProduct,AmountOrderProduct,imagePath,trackingOrder,newTrackingOrder);
        if (editTrackingNumber.equals("pass")) {
            try {
                dataSource.writeData(orderDataList);
                FXRouter.goTo("new_order");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า new_order ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("new_order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า new_order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
