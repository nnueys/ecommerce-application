package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.MemberDataFileDataSource;

import java.io.IOException;

public class ChangePasswordController {
    @FXML private Label notificationLabel;
    @FXML private PasswordField oldPasswordPasswordField;
    @FXML private PasswordField newPasswordPasswordField;
    @FXML private PasswordField confirmNewPasswordPasswordField;

    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    public void initialize() {
        // initialize จะถูกเรียกให้ทำงานเมื่อมีการ load Controller นี้
        System.out.println("initialize ChangePasswordController");
    }

    @FXML
    public void handleHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("market_place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void userChangePassword(ActionEvent actionEvent) {
        String oldPassword = oldPasswordPasswordField.getText();
        String newPassword = newPasswordPasswordField.getText();
        String confirmNewPassword = confirmNewPasswordPasswordField.getText();
        String name = memberData.getName();
        String username = memberData.getUsername();
        String password = memberData.getPassword();
        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";

        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        String changePasswordRecordStatus = memberDatalist.recordChangePassword(name, username, password, oldPassword, newPassword, confirmNewPassword);
        if ( changePasswordRecordStatus.equals("pass")) {
            try{
                dataSource.writeData(memberDatalist);
                FXRouter.goTo("login_page");
            } catch (IOException e) {
                System.err.println("ไปหน้า profile ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else {
            notificationLabel.setText(changePasswordRecordStatus);
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
