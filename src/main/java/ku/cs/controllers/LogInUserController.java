package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.AdminData;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.InputRegisterAccount;
import ku.cs.services.MemberDataFileDataSource;

import java.io.IOException;

public class LogInUserController {

    private InputRegisterAccount inputRegisterAccount = new InputRegisterAccount();

    private MemberDatalist memberDatalist;


    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Label notificationLabel;
    @FXML private ImageView adminImageView;


    @FXML
    public void handleUseRegisterUserButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("register_page");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }



    //login
    @FXML
    public void loginDone(ActionEvent actionEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        MemberData memberData = inputRegisterAccount.callUser(username,password);

        DataSource<MemberDatalist> dataSource;
        String directory = "member";
        String filename = "member.csv";
        dataSource = new MemberDataFileDataSource(directory, filename);
        MemberDatalist memberDatalist = dataSource.readData();

        if (inputRegisterAccount.userCheck(username, password)) {
            if ( memberData instanceof AdminData){
                notificationLabel.setText("only member can access this login");
            }
            else if (memberData instanceof MemberData){
                try {
                    if (memberData.isUserBan() == false) {
                        dataSource.writeData(memberDatalist);
                        FXRouter.goTo("market_place", memberData);
                    } else if(memberData.isUserBan() == true){
                        notificationLabel.setText("the account was banned");
                        memberData.setTryToLogin();
                        memberDatalist.recordTryToLogin(username, memberData.getTryToLogin());
                        dataSource.writeData(memberDatalist);
                    }
                    System.out.println(memberData);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("ไปที่หน้า market place ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
            }
        }else {
            notificationLabel.setText("incorrect username or password");
        }
    }

    @FXML
    public void handleAdminLoginPage(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("admin_login_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public  void handleTipsButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("application_advice");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า advice ไม่ได้");
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