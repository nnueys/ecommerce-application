package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.*;
import ku.cs.services.DataSource;
import ku.cs.services.InputProduct;
import ku.cs.services.ProductDataFileDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddProductController {

    @FXML private TextField nameAddProductShopTextField;
    @FXML private TextField descriptionProductShopTextField;
    @FXML private TextField priceProductShopTextField;
    @FXML private TextField quantityProductShopTextField;
    @FXML private ImageView productImageView;
    @FXML private Button AddProductPictureButton;
    @FXML private Label notificationLabel;
    @FXML private Button exitButton;


    final FileChooser fileChooser = new FileChooser();
    private ProductData productData;
    private MemberData memberData = (MemberData) FXRouter.getData();
    private InputProduct inputProduct = new InputProduct();
    private String imagePath;
    private ProductDatalist productDatalist;
    private ArrayList<ProductData> newProductData;
    private DataSource<ProductDatalist> dataSource;
    private GetList getList;
    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;
    private MemberDatalist memberDatalist;
    private OrderData orderData;
    private OrderDataList orderDataList;

    @FXML
    public void initialize(){
        System.out.println("initialize AddProductController");
        dataSource = new ProductDataFileDataSource("Product", "Product.csv");
        productDatalist = dataSource.readData();
    }

    @FXML
    public void handleUseConfirmAddProductButton(ActionEvent actionEvent) {

        String nameProductShop = nameAddProductShopTextField.getText();
        String descriptionProductShop = descriptionProductShopTextField.getText();
        double priceProductShop = Double.parseDouble(priceProductShopTextField.getText());
        int quantityProductShop = Integer.parseInt(quantityProductShopTextField.getText());

        if (nameAddProductShopTextField.getText().equals("")) {
            notificationLabel.setText("Please Enter Product Name");
        } else if (descriptionProductShopTextField.getText().equals("")) {
            notificationLabel.setText("Please Enter Product Description");
        } else if (priceProductShopTextField.getText().equals("")) {
            notificationLabel.setText("Please Enter Price");
        } else if (quantityProductShopTextField.getText().equals("")) {
            notificationLabel.setText("Please Enter Quantity");
        } else if (imagePath.equals("")) {
            notificationLabel.setText("Please Upload Picture");
        } else {
            String username = memberData.getUsername();
            String shopName = memberData.getShopName();

            ProductData productData = new ProductData(username, shopName, nameProductShop, descriptionProductShop, priceProductShop, quantityProductShop, imagePath);
            this.getList = new GetList(memberData, productData,memberDatalist,productDatalist,orderData,orderDataList,reviewProductData,reviewProductDataList);

            try {
                FXRouter.goTo("confirm_add_product", getList);
            } catch (IOException e) {
                System.err.println("ไปหน้า confirm ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }


    }

    @FXML
    public void handleBackProductToProductListButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("product_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button exitProductShopButton;
    public void handleExitProductShopButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitProductShopButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void handleAddProductPictureButton(ActionEvent event) {
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
                imagePath = destDir + "/" + filename;
//                dataSource.writeData(memberDatalist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
