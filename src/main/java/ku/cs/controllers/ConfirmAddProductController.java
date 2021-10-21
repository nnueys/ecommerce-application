package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.InputProduct;

import java.io.IOException;

public class ConfirmAddProductController {

    @FXML private Label nameConfirmLabel;
    @FXML private Label descriptionConfirmLabel;
    @FXML private Label priceConfirmLabel;
    @FXML private Label quantityConfirmLabel;
    @FXML private ImageView productImageView;


    private GetList getList = (GetList) FXRouter.getData();
    private ProductData productData;
    private MemberData memberData;
    private ProductDatalist productDatalist;
    private MemberDatalist memberDatalist;
    private InputProduct inputProduct = new InputProduct();

    @FXML
    private void initialize() {
        System.out.println("initialize ConfirmAddProductController");
        productData = getList.getProductData();
        memberData = getList.getMemberData();
        showProductShopData();
    }

    @FXML
    public void handleUseConfirmButton(ActionEvent actionEvent) throws IOException {

        String username = memberData.getUsername();
        String shopName = memberData.getShopName();

        String productRecordStatus = inputProduct.productData(username, shopName, productData.getNameProduct(), productData.getDescriptionProduct(), productData.getPriceProduct(), productData.getQuantityProduct(), productData.getImagePath());

        if (productRecordStatus.equals("pass")) {
            try {
                FXRouter.goTo("product_list");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า shop ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }


    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("add_product");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า productShop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    private void showProductShopData(){
        nameConfirmLabel.setText(productData.getNameProduct());
        descriptionConfirmLabel.setText(productData.getDescriptionProduct());
        priceConfirmLabel.setText(String.valueOf(productData.getPriceProduct()));
        quantityConfirmLabel.setText(String.valueOf(productData.getQuantityProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
    }


    @FXML
    private Button exitConfirmButton;
    public void handleExitConfirmButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitConfirmButton.getScene().getWindow();
        stage.close();
    }
}