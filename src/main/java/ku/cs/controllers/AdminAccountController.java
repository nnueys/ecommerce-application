package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.AdminData;

import java.io.IOException;

public class AdminAccountController {

    private AdminData memberData = (AdminData) FXRouter.getData();

    @FXML
    public void handleUseAdminMemberButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("admin_member", memberData);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า admin_member ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUseAdminReportButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("admin_report");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_report ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleUseMemberProjectButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("member_project");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า member_project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleUseChangePasswordButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("change_password", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า change_password ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    private Button exitButton;

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handleUseBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
