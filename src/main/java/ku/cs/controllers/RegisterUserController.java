package ku.cs.controllers;

import javafx.collections.ObservableList;
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
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.InputRegisterAccount;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class RegisterUserController {
    @FXML private TextField nameTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordPasswordField;
    @FXML private TextField confirmPasswordPasswordField;
    @FXML private Label notificationLabel;
    @FXML private ImageView profilePictureImageView;


    private InputRegisterAccount inputRegisterAccount = new InputRegisterAccount();
    private DataSource<MemberDatalist> dataSource;
    private MemberDatalist memberDatalist;
    private ObservableList<MemberData> memberDataObservableList;
    private MemberData memberData;
    private String imagePath;
    private String localDateTime;

    @FXML
    public void initialize(){
        showMemberData();
    }

    @FXML
    public void handleUseBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login_page");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    private void showMemberData() {
        profilePictureImageView.setImage(new Image("file:"+"images/default_pfp.JPG", true));
    }

    @FXML
    private void userRegister(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String username = userNameTextField.getText();
        String password = passwordPasswordField.getText();
        String confirmPassword = confirmPasswordPasswordField.getText();
        String memberRecordStatus = inputRegisterAccount.memberData(name, username, password, confirmPassword,"-", imagePath);
        if (memberRecordStatus.equals("pass")) {
            try {
                FXRouter.goTo("login_page");
            } catch (IOException e) {
                System.err.println("ไปหน้า login ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }else {
            notificationLabel.setText(memberRecordStatus);
        }
    }

    @FXML
    private Button exitButton;

    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleUploadProfilePictureButton(ActionEvent event) {
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
                profilePictureImageView.setImage(new Image(target.toUri().toString()));
                imagePath = destDir + "/" + filename;
//                dataSource.writeData(memberDatalist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
