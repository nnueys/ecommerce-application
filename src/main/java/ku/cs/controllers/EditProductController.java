package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataFileDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class EditProductController {

    @FXML private TextField nameEditTextField;
    @FXML private TextField descriptionEditTextField;
    @FXML private TextField priceEditTextField;
    @FXML private TextField quantityEditTextField;
    @FXML private ImageView productImageView;
    @FXML private Button changeProductImageButton;

    private DataSource<ProductDatalist> dataSource;
    private ProductDatalist productDatalist;
    private ObservableList<ProductData> productDataObservableList;
    private String imagePath;
    private ProductDatalist dataList;
    private ProductData productData = (ProductData) FXRouter.getData();
    private GetList getList;
    private MemberData memberData;
    private MemberDatalist memberDatalist;
    private OrderData orderData;
    private OrderDataList orderDataList;
    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;

    @FXML
    private void initialize() {
        System.out.println("initialize EditProductController");
        dataSource = new ProductDataFileDataSource("Product","Product.csv");
        dataList = dataSource.readData();
        showProduct();
    }

    private void showProduct() {
        productImageView.setImage(new Image("file:"+productData.getImagePath(),true));
        nameEditTextField.setText(productData.getNameProduct());
        priceEditTextField.setText(String.valueOf(productData.getPriceProduct()));

    }

    @FXML
    public void handleUseEditButton(ActionEvent actionEvent) {
        String username = productData.getUsername();
        String shopName = productData.getShopName();
        String productName = productData.getNameProduct();
        String productDescription = productData.getDescriptionProduct();
        double productPrice = productData.getPriceProduct();
        int productQuantity = productData.getQuantityProduct();
        String imagePath = productData.getImagePath();

        String newProductName = nameEditTextField.getText();
        double newProductPrice = Double.parseDouble(priceEditTextField.getText());

        productData.setNameProduct(newProductName);
        productData.setPriceProduct(newProductPrice);

        ProductData productData = new ProductData(username, shopName, productName, productDescription, productPrice,productQuantity,imagePath,newProductName,newProductPrice);

        this.getList = new GetList(memberData, productData, memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);
        try {
            FXRouter.goTo("confirm_edit_image_product", getList);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า confirm_edit_image_product ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void AddQuantityButton(ActionEvent actionEvent) {
        String username = productData.getUsername();
        String shopName = productData.getShopName();
        String productName = productData.getNameProduct();
        String productDescription = productData.getDescriptionProduct();
        double productPrice = productData.getPriceProduct();
        String imagePath = productData.getImagePath();
        int quantity = Integer.parseInt(quantityEditTextField.getText());

        if (quantity > 0) {
            productData.addProductQuantity(quantity);
            int productQuantity = productData.getQuantityProduct();
            ProductData productData = new ProductData(username, shopName, productName, productDescription, productPrice,productQuantity,imagePath);

            this.getList = new GetList(memberData, productData, memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);

            try {
                FXRouter.goTo("confirm_edit_product", getList);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า confirm edit ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    @FXML
    public void handleBackEditButton (ActionEvent actionEvent){
        try {
            FXRouter.goTo("product_data_for_seller",productData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product Data ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleChangeProductImageButton (ActionEvent event){
        DataSource<ProductDatalist> dataSource;
        dataSource = new ProductDataFileDataSource();
        ProductDatalist productDatalist = dataSource.readData();

        FileChooser chooser = new FileChooser();
        // SET FILECHOOSER INITIAL DIRECTORY
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        // DEFINE ACCEPTABLE FILE EXTENSION
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        // GET FILE FROM FILECHOOSER WITH JAVAFX COMPONENT WINDOW
        Node source = (Node) event.getSource();
        File file = chooser.showOpenDialog(source.getScene().getWindow());
        if (file != null){
            try {
                // CREATE FOLDER IF NOT EXIST
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();
                // RENAME FILE
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename
                );
                // COPY WITH FLAG REPLACE FILE IF FILE IS EXIST
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                // SET NEW FILE PATH TO IMAGE
                productImageView.setImage(new Image(target.toUri().toString()));
                productData.setImagePath(destDir + "/" + filename);
                for(ProductData product : productDatalist.getAllProducts()) {
                    if ((/*product.getUsername().equals(productData.getUsername()) && */productData.getNameProduct().equals(productData.getNameProduct()) && product.getDescriptionProduct().equals(productData.getDescriptionProduct() ))) {
                        product.setImagePath(destDir + "/" + filename);
                        dataSource.writeData(productDatalist);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private Button exitEditButton;
    public void handleExitEditButton (ActionEvent actionEvent){
        Stage stage = (Stage) exitEditButton.getScene().getWindow();
        stage.close();
    }


}
