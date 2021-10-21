//package ku.cs.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import ku.cs.models.*;
//import ku.cs.service.InputShopName;
//import ku.cs.service.InputNameStore;
//
//import java.io.IOException;
//
//public class OpenShopController {
//
//    @FXML private TextField nameShopTextField;
//
//
//    private ShopNameData shopNameData;
//
//
//    private InputNameStore inputNameStore = new InputNameStore();
//
//    private InputShopName inputShopName = new InputShopName();
//
//    @FXML
//    public void initialize(){
//
//    }
//
//
//    @FXML
//    public void handleUseSellerButton(ActionEvent actionEvent) throws IOException{
//
//        String nameStore = nameShopTextField.getText();
//
//        ShopNameData data1 = inputShopName.shopNameData(nameStore);
//
//        try {
//            FXRouter.goTo("shop", data1);
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า shop ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//
//    }
//
//    @FXML
//    private Button exitButton;
//
//    public void handleExitButton(ActionEvent actionEvent) {
//        Stage stage = (Stage) exitButton.getScene().getWindow();
//        stage.close();
//    }
//
//}
package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.MemberDataFileDataSource;

import java.io.IOException;

public class OpenShopController {

    @FXML private TextField nameShopTextField;

    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    public void initialize(){
        System.out.println("initialize OpenShopController");
    }

    @FXML
    public void handleUseSellerButton(ActionEvent actionEvent) {
        String shopName = nameShopTextField.getText();
        String username = memberData.getUsername();
        String password = memberData.getPassword();
        String oldShopName = memberData.getShopName();
        String name = memberData.getName();

        memberData.setShopName(shopName);

        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        String shopNameRecordStatus = memberDatalist.recordShopName(name, username, password, oldShopName, shopName);
        if (shopNameRecordStatus.equals("pass")){
            try {
                dataSource.writeData(memberDatalist);
                FXRouter.goTo("product_list", memberData);
            } catch (IOException e) {
                System.err.println("ไปที่หน้า shop ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
    }

    public void handleUseBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("my_profile_page", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    private Button exitButton;
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}


