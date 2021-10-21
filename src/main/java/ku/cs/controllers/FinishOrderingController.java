package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;

import java.io.IOException;

public class FinishOrderingController {


    @FXML private Button exitButton;

    private MemberData memberData = (MemberData) FXRouter.getData();


    @FXML
    public void initialize() {
        System.out.println("initialize FinishOrderingController");
    }

    @FXML
    public void backButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("market_place",memberData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า marketPlace ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleHomeButton(MouseEvent mouseEvent) {
        try {
            FXRouter.goTo("market_place");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }





}
