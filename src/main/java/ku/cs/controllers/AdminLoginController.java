package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.AdminData;
import ku.cs.models.MemberData;
import ku.cs.services.InputRegisterAccount;

import java.io.IOException;

public class AdminLoginController {

    private InputRegisterAccount inputRegisterAccount = new InputRegisterAccount();

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label notificationLabel;

    //login
    @FXML
    public void loginDone (ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        MemberData memberData = inputRegisterAccount.callUser(username,password);
        if (inputRegisterAccount.userCheck(username, password)) {
            if ( memberData instanceof AdminData){
                try {
                    FXRouter.goTo("admin_account", memberData);
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า admin ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
            }
            else if (memberData instanceof MemberData){
                notificationLabel.setText("only admin can access this login");
            }
        }else {
            notificationLabel.setText("incorrect username or password");
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
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
