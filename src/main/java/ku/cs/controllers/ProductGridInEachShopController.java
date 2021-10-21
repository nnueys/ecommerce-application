package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;

import java.io.IOException;

public class ProductGridInEachShopController {


    @FXML
    private ImageView productImageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;



    private MemberData memberData;
    private ProductData productData;
    private ProductDatalist productDatalist;
    private MemberDatalist memberDatalist;
    private OrderData orderData;
    private OrderDataList orderDataList;
    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;
    private GetList getList = (GetList) FXRouter.getData();

    @FXML
    public void initialize(){
        memberData = getList.getMemberData();
        productData = getList.getProductData();
        memberDatalist = getList.getMemberDatalist();
        productDatalist = getList.getProductDatalist();

        System.out.println("initialize ProductGridInEachShopController");
    }

    public void showProductData(ProductData productData) {
        this.productData = productData;
        nameLabel.setText(productData.getNameProduct());
        priceLabel.setText(String.valueOf(productData.getPriceProduct()));
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
        getList = new GetList(memberData,productData, memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);

    }

    @FXML
    public void orderButton(MouseEvent event) {
        try {
            FXRouter.goTo("order",getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order ไม่ได้");
            e.printStackTrace();
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
