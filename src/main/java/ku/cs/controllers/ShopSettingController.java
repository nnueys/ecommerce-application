package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.InputProduct;
import ku.cs.services.MemberDataFileDataSource;

import java.io.IOException;

public class ShopSettingController {
    @FXML private TextField limitAmountTextField;
    @FXML private Button exitButton;

    private MemberData memberData = (MemberData) FXRouter.getData();
    private ProductData productData;
    private ProductDatalist productDatalist;
    private MemberDatalist memberDatalist;
    private InputProduct inputProduct = new InputProduct();

    @FXML
    private void initialize() {
        System.out.println("initialize ShopSettingController");
        showLimitProduct();
    }

    private void showLimitProduct() {
        limitAmountTextField.setText(String.valueOf(memberData.getLimit()));
    }

    @FXML
    void handleUseSellerButton(ActionEvent event) {
        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        String name = memberData.getName();
        String username = memberData.getUsername();
        String password = memberData.getPassword();
        String shopName = memberData.getShopName();
        int limit = memberData.getLimit();

        int newLimit = Integer.parseInt(limitAmountTextField.getText());
        memberData.setLimit(newLimit);

        memberDatalist.recordLimit(name, username, password, shopName, newLimit);
        try {
            dataSource.writeData(memberDatalist);
            System.err.println(limit);
            System.err.println(newLimit);
            FXRouter.goTo("product_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า shop ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("product_list");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า product_list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}