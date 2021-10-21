package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.OrderData;
import ku.cs.models.OrderDataList;
import ku.cs.models.ProductData;
import ku.cs.services.DataSource;
import ku.cs.services.OrderDataFileDataSource;

import java.io.BufferedReader;
import java.io.IOException;

public class ShippingController {


    @FXML private GridPane shipGrid;
    @FXML private Label nameShopLabel;
    @FXML private Button exitButton;

    final FileChooser fileChooser = new FileChooser();
    private OrderData orderData;
    private ProductData productData;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private void initialize() {
        System.out.println("initialize ShippingController");
        showShopNameData();
        showShippingData();
    }

    private void showShopNameData(){
        nameShopLabel.setText(memberData.getShopName());
    }

    @FXML
    public void handleUseOrdersButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("new_order",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUseProductListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("product_list",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleHomeButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("market_place", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private void showShippingData() {
        DataSource<OrderDataList> dataSource;
        String directory = "Order";
        String filename = "Order.csv";

        dataSource = new OrderDataFileDataSource(directory, filename);
        OrderDataList orderDataList = dataSource.readData();

        OrderDataList orderDataList1 = new OrderDataList();
        for(OrderData orderData1 : orderDataList.getNewOrderProductData()){
            if (orderData1.getShopName().equals(memberData.getShopName())){
                orderDataList1.addOrderProduct(orderData1);
            }
        }
        shipGrid.getChildren().clear();
        int column = 0 ;
        int row = 1;
        try {
            for(OrderData orderData:orderDataList1.getNewOrderProductData()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/shipping_product_grid.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ShippingProductGridController shippingProductGridController =fxmlLoader.getController();

                if(!orderData.getTrackingNumber().equals("-")){
                    shippingProductGridController.showShipData(orderData);
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    shipGrid.add(anchorPane,column++,row);  //(child,column,row)
                    //set grid width
                    shipGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    shipGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    shipGrid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    shipGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    shipGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    shipGrid.setMaxHeight(Region.USE_PREF_SIZE);

                    //ระยะห่างของ product แต่ละชิ้น
                    GridPane.setMargin(anchorPane,new Insets(13));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
