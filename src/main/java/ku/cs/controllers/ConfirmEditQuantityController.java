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

public class ConfirmEditQuantityController {

    @FXML private Label nameSaveEditLabel;
    @FXML private Label descriptionSaveEditLabel;
    @FXML private Label priceSaveEditLabel;
    @FXML private Label quantitySaveEditLabel;
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
        showProductConfirmEditData();
    }

    private void showProductConfirmEditData(){
        nameSaveEditLabel.setText(productData.getNameProduct());
        priceSaveEditLabel.setText(String.valueOf(productData.getPriceProduct()));
        quantitySaveEditLabel.setText(String.valueOf(productData.getQuantityProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
    }

    @FXML
    public void handleUseConfirmEditButton(ActionEvent actionEvent) {
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

        String editAddQuantityRecordStatus = productDatalist.recordAddQuantityProduct(username, shopName, productName, productDescription, productPrice, productQuantity);
        if (editAddQuantityRecordStatus .equals("pass")) {
            try {
                dataSource.writeData(productDatalist);
                FXRouter.goTo("product_list"); // FXRouter.goTo("product_list",productDatalist);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า product_list ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleBackToEditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("product_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private Button exitSaveEditButton;
    public void handleExitSaveEditButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitSaveEditButton.getScene().getWindow();
        stage.close();
    }

}