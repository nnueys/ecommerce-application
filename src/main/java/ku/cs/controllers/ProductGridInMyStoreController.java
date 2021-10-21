package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;

import java.io.IOException;

public class ProductGridInMyStoreController {

    @FXML private Label nameGoodsLabel;
    @FXML private Label priceGoodsLabel;
    @FXML private Label amountGoodsLabel;
    @FXML private Label remainLabel;
    @FXML private ImageView productImageView;

    private MemberDatalist memberDatalist;
    private ProductDatalist productDatalist;
    private ProductData productData;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private void initialize() {
        System.out.println("initialize ProductGridInMyStoreController");
    }

    public void showGoodsData(ProductData productData) {
        this.productData = productData;
        nameGoodsLabel.setText(productData.getNameProduct());
        priceGoodsLabel.setText(String.valueOf(productData.getPriceProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));

        if (productData.limitAmount(productData.getQuantityProduct(), memberData.getLimit()).equals("red")){
            amountGoodsLabel.setText(String.valueOf(productData.getQuantityProduct()));
            amountGoodsLabel.setStyle("-fx-text-fill: #cf0c0c");
            remainLabel.setStyle("-fx-text-fill: #cf0c0c");
        } else {
            amountGoodsLabel.setText(String.valueOf(productData.getQuantityProduct()));
        }
    }

    @FXML
    public void handleEditButton(MouseEvent event) {
        try {
            FXRouter.goTo("product_data_for_seller", productData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า productData ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
