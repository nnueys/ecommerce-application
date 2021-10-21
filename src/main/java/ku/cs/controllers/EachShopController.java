package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.GetList;
import ku.cs.models.MemberData;
import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataFileDataSource;

import java.io.IOException;
import java.util.ArrayList;


public class EachShopController {
    @FXML
    private Button exitButton;
    @FXML
    private GridPane productMarketShopGrid;
    @FXML
    private Label nameShopLabel;

    private ProductDatalist productDatalist;
    private ArrayList<ProductData> productItem;
    private MemberData memberData;
    private ProductData productData;
    private GetList getList = (GetList) FXRouter.getData();

    public void initialize(){
        System.out.println("initialize MarketShop");

        memberData = getList.getMemberData();
        productData = getList.getProductData();

        nameShopLabel.setText(productData.getShopName());

        ShowProduct();
    }

    public void ShowProduct(){
        DataSource<ProductDatalist> dataSource;
        String directory = "Product";
        String filename = "Product.csv";

        dataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = dataSource.readData();

        ProductDatalist productDatalist1 = new ProductDatalist();
        for(ProductData productData1 : productDatalist.getProductData()){
            if (productData1.getShopName().equals(productData.getShopName())){
                productDatalist1.addProduct(productData1);
            }
        }

        int column = 0 ;
        int row = 2;
        try {

            for(ProductData productData: productDatalist1.getProductData())
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/product_grid_in_each_shop.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ProductGridInEachShopController productGridInEachShopController = fxmlLoader.getController();
                productGridInEachShopController.showProductData(productData);

                if(column == 3){
                    column = 0;
                    row++;
                }
                productMarketShopGrid.add(anchorPane,column++,row);  //(child,column,row)
                //ตั้งค่าของที่จะขาย
                productMarketShopGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                productMarketShopGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                productMarketShopGrid.setMaxWidth(Region.USE_PREF_SIZE);


                productMarketShopGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                productMarketShopGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                productMarketShopGrid.setMaxHeight(Region.USE_PREF_SIZE);


                //ระยะห่างของ product แต่ละชิ้น
                GridPane.setMargin(anchorPane,new Insets(13));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            FXRouter.goTo("order");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
