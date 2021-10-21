package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataFileDataSource;

import java.io.IOException;

public class ConfirmEditProductController {

    @FXML private Label nameEditSaveProductLabel;
    @FXML private Label priceEditSaveProductLabel;
    @FXML private Label quantityEditSaveProductLabel;
    @FXML private Button exitSaveEditButton;
    @FXML private ImageView productImageView;

    private GetList getList = (GetList) FXRouter.getData();
    private ProductData productData;
    private EditProductController editProductController;
    private ProductDatalist productDatalist;
    private DataSource<ProductDatalist> dataSource;
    private ObservableList<ProductData> productDataObservableList;
    private String imagePath;
    private ProductDatalist dataList;
    private MemberData memberData;
    private MemberDatalist memberDatalist;
    private OrderData orderData;
    private OrderDataList orderDataList;

    @FXML
    private void initialize() {
        System.out.println("initialize ConfirmEditQuantityController");
        productData = getList.getProductData();
        showProductConfirmEditProductData();
    }

    private void showProductConfirmEditProductData(){
        nameEditSaveProductLabel.setText(productData.getNewProductName());
        //descriptionSaveEditLabel.setText(productData.getDescriptionProduct());
        priceEditSaveProductLabel.setText(String.valueOf(productData.getNewProductPrice()));
        quantityEditSaveProductLabel.setText(String.valueOf(productData.getQuantityProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
    }

    @FXML
    void handleBackToEditProductButton(ActionEvent event) {
        try {
            FXRouter.goTo("edit_product",productData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


    @FXML
    void handleUseConfirmEditProductButton(ActionEvent event) {

        String username = productData.getUsername();
        String shopName = productData.getShopName();
        String productName = productData.getNameProduct();
        String productDescription = productData.getDescriptionProduct();
        double productPrice = productData.getPriceProduct();
        int productQuantity = productData.getQuantityProduct();

        DataSource<ProductDatalist> dataSource;
        String directory = "Product";
        String filename = "Product.csv";

        dataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = dataSource.readData();

        String editProductRecordStatus = productDatalist.recordEditProduct(username, productName, productDescription,productPrice, productData.getNewProductName(), productData.getNewProductPrice());
        if (editProductRecordStatus.equals("pass")) {
            try {
                dataSource.writeData(productDatalist);
                FXRouter.goTo("product_list");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า product_list ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    void handleExitSaveEditButton(ActionEvent event) {
        Stage stage = (Stage) exitSaveEditButton.getScene().getWindow();
        stage.close();
    }

}
