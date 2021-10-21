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
import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataFileDataSource;

import java.io.IOException;

public class MyStoreController {

    @FXML private Label nameShopLabel;
    @FXML private GridPane goodsGrid;
    @FXML private Button exitButton;

    final FileChooser fileChooser = new FileChooser();
    private ProductData productData;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private void initialize() {
        System.out.println("initialize MyStoreController");
        showShopNameData();
        showProductData();
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void showShopNameData(){
        nameShopLabel.setText(memberData.getShopName());
    }

    @FXML
    public void handleUseAddProductButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("add_product", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า productShop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUseShopOrderButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("new_order", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า new_order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUseOrderDeliveredButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order_delivered",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order_delivered ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemainSettingButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("shop_setting", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า setting ไม่ได้");
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


    private void showProductData() {
        DataSource<ProductDatalist> dataSource;
        String directory = "Product";
        String filename = "Product.csv";

        dataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = dataSource.readData();

        ProductDatalist productDatalist1 = new ProductDatalist();
        for(ProductData productData1 : productDatalist.getProductData()){
            if (productData1.getUsername().equals(memberData.getUsername())){
                productDatalist1.addProduct(productData1);
            }
        }

        int column = 0 ;
        int row = 2;
        try {
            for(ProductData productData:productDatalist1.getProductData()) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/product_grid_in_my_store.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProductGridInMyStoreController productGridInMyStoreController =fxmlLoader.getController();
                productGridInMyStoreController.showGoodsData(productData);

                if(column == 3){
                    column = 0;
                    row++;
                }
                goodsGrid.add(anchorPane,column++,row);  //(child,column,row)
                //set grid width
                goodsGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                goodsGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                goodsGrid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                goodsGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                goodsGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                goodsGrid.setMaxHeight(Region.USE_PREF_SIZE);

                //ระยะห่างของ product แต่ละชิ้น
                GridPane.setMargin(anchorPane,new Insets(15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
