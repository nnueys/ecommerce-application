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

import java.io.IOException;

public class NewOrderController {


    @FXML private GridPane orderGrid;
    @FXML private Label nameShopLabel;
    @FXML private Button exitButton;

    final FileChooser fileChooser = new FileChooser();
    private OrderData orderData;
    private ProductData productData;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    public void initialize(){
        System.out.println("initialize NewOrderController ");
        showShopNameData();
        showNewOrderData();
    }

    private void showShopNameData(){
        nameShopLabel.setText(memberData.getShopName());
    }

    @FXML
    public void handleUseDeliveredButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order_delivered",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shipping ไม่ได้");
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

    private void showNewOrderData() {
        DataSource<OrderDataList> dataSource;
        String directory = "Order";
        String filename = "Order.csv";

        dataSource = new OrderDataFileDataSource(directory, filename);
        OrderDataList orderDataList = dataSource.readData();

        OrderDataList orderDataList1 = new OrderDataList();
        for(OrderData orderData1 : orderDataList.getNewOrderProductData()){
            if (orderData1.getShopName().equals(memberData.getShopName()))
                if(orderData1.getTrackingNumber().equals("-")){
                    orderDataList1.addOrderProduct(orderData1);
                }

        }

        int column = 0 ;
        int row = 1;
        try {
            for(OrderData orderData1:orderDataList1.getNewOrderProductData()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/new_order_grid.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                NewOrderGridController newOrderGridController = fxmlLoader.getController();
                newOrderGridController.showOrderData(orderData1);

                if(column == 1){
                    column = 0;
                    row++;
                }
                orderGrid.add(anchorPane,column++,row);  //(child,column,row)
                //set grid width
                orderGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                orderGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                orderGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                orderGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                orderGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                orderGrid.setMaxHeight(Region.USE_PREF_SIZE);

                //ระยะห่างของ product แต่ละชิ้น
                GridPane.setMargin(anchorPane,new Insets(13));
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
