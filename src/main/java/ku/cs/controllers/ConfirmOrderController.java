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
import ku.cs.services.DataSource;
import ku.cs.services.InputOrder;
import ku.cs.services.ProductDataFileDataSource;

import java.io.IOException;

public class ConfirmOrderController {
    @FXML private Label totalPrice;
    @FXML private Label nameProduct;
    @FXML private Label amountProduct;
    @FXML private ImageView productImageView;
    @FXML private Button exitButton;


    private ProductData productData;
    private ProductDatalist productDatalist;
    private MemberData memberData;
    private MemberDatalist memberDatalist;
    private OrderData orderData;
    private GetList getList = (GetList) FXRouter.getData();
    private InputOrder inputOrder = new InputOrder();
    private DataSource<ProductDatalist> productDatalistDataSource;

    @FXML
    private void initialize() {
        System.out.println("initialize ConfirmOrderController");
        memberData = getList.getMemberData();
        productData = getList.getProductData();
        productDatalist = getList.getProductDatalist();
        orderData = getList.getOrderData();

        showProductOrderData();

    }
    @FXML
    public void ConfirmButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order_already");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า orderAlready ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
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
    private void showProductOrderData(){
        nameProduct.setText(productData.getNameProduct());
        amountProduct.setText(String.valueOf(orderData.getOrderProductOrder()));
        totalPrice.setText(String.valueOf(orderData.getTotalPriceOrder()));
        productImageView.setImage(new Image("file:"+orderData.getImagePath(),true));

    }
    public void handleUseConfirmAddOrderProductButton(ActionEvent actionEvent)  {
        productData.minusFromBuyProduct(orderData.getOrderProductOrder());
        System.out.println(orderData.getOrderProductOrder());
        String username = productData.getUsername();
        String shopName = productData.getShopName();
        String productName = productData.getNameProduct();
        String productDescription = productData.getDescriptionProduct();
        double productPrice = productData.getPriceProduct();
        int productQuantity = productData.getQuantityProduct();


        String productRecordOrder = inputOrder.orderData(memberData.getUsername(), productData.getShopName(),orderData.getNameProduct(), orderData.getPriceProduct(), orderData.getOrderProductOrder(),orderData.getImagePath(),"-");

        String directory = "Product";
        String filename = "Product.csv";

        productDatalistDataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = productDatalistDataSource.readData();

        productDatalist.recordBuyProduct(username,shopName,productName,productDescription,productPrice,productData.getQuantityProduct(),productQuantity);

        if (productRecordOrder.equals("pass")) {
            System.out.println(productQuantity);
            try {
                productDatalistDataSource.writeData(productDatalist);
                FXRouter.goTo("order_already" );
            } catch (IOException e) {
                System.err.println("ไปหน้า order_already ไม่ได้");
                e.printStackTrace();
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }

        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}

