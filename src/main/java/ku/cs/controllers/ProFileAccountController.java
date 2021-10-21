package ku.cs.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.GetList;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.MemberDataFileDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ProFileAccountController {

    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML private Label nameLabel;
    @FXML private Label usernameLabel;
    @FXML private Button openFileButton;
    @FXML private ImageView profilePictureImageView;
    @FXML private Button exitButton;

    private GetList getList;
    private DataSource<MemberDatalist> dataSource;
    private MemberDatalist memberDatalist;
    private ObservableList<MemberData> memberDataObservableList;
    private String imagePath;

    @FXML
    public void initialize() {
        // initialize จะถูกเรียกให้ทำงานเมื่อมีการ load Controller นี้
        System.out.println("initialize ProFileAccountController");
        System.out.println(System.getProperty("user.dir"));
        showMemberData();
    }

    private void showMemberData() {
        nameLabel.setText(memberData.getName());
        usernameLabel.setText(memberData.getUsername());
        profilePictureImageView.setImage(new Image("file:"+memberData.getImagePath(), true));
    }

    @FXML
    public void handleChangeProfilePictureButton(ActionEvent event) {
        DataSource<MemberDatalist> dataSource;

        dataSource = new MemberDataFileDataSource();
        MemberDatalist memberDatalist = dataSource.readData();

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

                for (MemberData member : memberDatalist.getAllMembers()) {
                    if (member.getUsername().equals(memberData.getUsername())) {
                        member.setImagePath(destDir + "/" + filename);
                        dataSource.writeData(memberDatalist);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void handleChangePasswordButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("change_password", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเปลี่ยนรหัสไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleOpenShopButton(ActionEvent actionEvent) {

        String pages;
        if (memberData.getShopName().equals("null")) {
            pages = "open_shop";
        } else {
            pages = "product_list";
        }

        try {
            FXRouter.goTo(pages, memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้าเปิดร้านไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

//    @FXML
//    public void handleOpenShopButton(ActionEvent actionEvent) {
//        try {
//            FXRouter.goTo("open_shop", memberData);
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้าเปิดร้านไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//        }
//    }

    @FXML
    public void handleHomeButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("market_place", memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleLogoutButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login_page");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


    @FXML
    public void handleMyShoppingButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("order_list",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า order_list ไม่ได้");
            e.printStackTrace();
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
