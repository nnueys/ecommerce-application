package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ReviewProductFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class OrderController {

    @FXML private Button exitButton;
    @FXML private Label nameProduct;
    @FXML private Label priceProduct;
    @FXML private Label amountProduct;
    @FXML private Label descriptionProduct;
    @FXML private TextField orderProduct;
    @FXML private Label notificationLabel;
    @FXML private Label nameShop;
    @FXML private ImageView productImageView;

    @FXML private Label averageStarLabel;
    @FXML private ListView reviewListView;
    @FXML private Label countPeople;
    @FXML private Button reportReview;




    final FileChooser fileChooser = new FileChooser();
    private GetList getList = (GetList) FXRouter.getData();
    private ProductData productData;
    private ProductDatalist productDatalist;
    private MemberData memberData;

    private ReviewProductDataList reviewProductDataList;
    private ReviewProductFileDataSource reviewProductFileDataSource;
    private ArrayList<ReviewProductData> reviewProductDataArrayList;
    private ArrayList<ReviewProductData> productReview ;
    private MemberDatalist memberDatalist;
    private DataSource <OrderDataList> dataSource;
    private DataSource <ProductDatalist> productDatalistDataSource;
    private OrderData orderData;
    private OrderDataList orderDataList;
    private ReviewProductData reviewProductData;


    @FXML
    public void initialize(){
        this.memberData = getList.getMemberData();
        this.productData = getList.getProductData();
        this.productDatalist = getList.getProductDatalist();
        this.reviewProductFileDataSource = new ReviewProductFileDataSource();
        this.reviewProductDataList = reviewProductFileDataSource.readData();
        this.reviewProductDataArrayList = reviewProductDataList.newReviewProduct();

        System.out.println("initialize Order");

        showProductOrderData();
        showListView();
        handleSelectedReportReviewListView();
    }

    @FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("market_place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketPlace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void reportProductButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("report_product",getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า report_product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void shopMarket(MouseEvent event) {
        try {
            FXRouter.goTo("market_shop",getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า market_shop ไม่ได้");
            e.printStackTrace();
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    private void showProductOrderData(){
        nameProduct.setText(productData.getNameProduct());
        amountProduct.setText(String.valueOf(productData.getQuantityProduct()));
        priceProduct.setText(String.valueOf(productData.getPriceProduct()));
        descriptionProduct.setText(productData.getDescriptionProduct());
        nameShop.setText(productData.getShopName());
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));

    }


    private void showListView(){
        reviewListView.getItems().addAll(productReview());
        averageStarLabel.setText(String.format("%.1f",reviewProductDataList.averageStars(productReview)));
        countPeople.setText(String.valueOf(reviewProductDataList.peopleTotal(productReview)));

    }
    private void handleSelectedReportReviewListView() {

        reviewListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ReviewProductData>() {
                    @Override
                    public void changed(ObservableValue<? extends ReviewProductData> observable,
                                        ReviewProductData oldValue, ReviewProductData newValue) {
                        System.out.println(newValue + " is selected");
                        getSelectedReportReviewListView(newValue);
                    }
                });
    }
    private void getSelectedReportReviewListView(ReviewProductData reviewProductData){
        String shopName = reviewProductData.getShopName();
        String usernameCustomer = reviewProductData.getUsername();
        String product = reviewProductData.getNameProduct();
        double star = reviewProductData.getStar();
        String reviewText = reviewProductData.getReviewText();

        reviewProductData = new ReviewProductData(usernameCustomer,product,shopName,star,reviewText);
        this.getList = new GetList(memberData,productData,memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);
    }

    @FXML
    public void reportReviewButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("report_review_product",getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า reviewProduct ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public ArrayList<ReviewProductData> productReview(){
        productReview = new ArrayList<>();
        for (ReviewProductData reviewProductData : reviewProductDataList.newReviewProduct()) {
            if (productData.getNameProduct().equals(reviewProductData.getNameProduct())&&productData.getShopName().equals(reviewProductData.getShopName())){
                productReview.add(reviewProductData);
            }
        }
        return productReview;
    }

    @FXML
    public void handleUseOrderProductButton(ActionEvent actionEvent)  {
        if (orderProduct.getText().equals("")){
            notificationLabel.setText("please enter quantity");
            return;
        }
        else {
            String nameProductOrder = productData.getNameProduct();
            double priceProductOrder = productData.getPriceProduct();
            int orderProductOrder = Integer.parseInt(orderProduct.getText());
            String imagePath = productData.getImagePath();
            System.out.println(imagePath);

            if(orderProductOrder > productData.getQuantityProduct() || orderProductOrder <= 0){
                notificationLabel.setText("you have reached the maximum quantity");
            } else {
                OrderData orderData = new OrderData(memberData.getUsername() ,productData.getShopName(),nameProductOrder, priceProductOrder, orderProductOrder,imagePath,"null");
                this.getList = new GetList(memberData,productData,memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);
                try {
                    FXRouter.goTo("confirm_order",getList);
                } catch (IOException e) {
                   System.err.println("ไปหน้า confirm_order ไม่ได้");
                   e.printStackTrace();
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }

            }

        }
    }

}










