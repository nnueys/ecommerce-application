package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.ProductData;

import java.io.IOException;

public class ProductDataController {
    @FXML private Label nameProductDataLabel;
    @FXML private Label descriptionProductDataLabel;
    @FXML private Label quantityProductDataLabel;
    @FXML private Label priceProductDataLabel;
    @FXML private ImageView productImageView;
    @FXML private Button exitButton;


    private MemberData memberData;
    private ProductData productData = (ProductData) FXRouter.getData();

    @FXML
    public void initialize(){
        System.out.println("initialize ProductDataController");

        showProductData();
    }

    @FXML
    public void handleUseEditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("edit_product", productData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า edit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleBackToProductListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("product_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private void showProductData(){
        nameProductDataLabel.setText(productData.getNameProduct());
        descriptionProductDataLabel.setText(productData.getDescriptionProduct());
        quantityProductDataLabel.setText(String.valueOf(productData.getQuantityProduct()));
        priceProductDataLabel.setText(String.valueOf(productData.getPriceProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
