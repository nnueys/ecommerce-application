package ku.cs.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.models.ReportProductData;
import ku.cs.models.ReportProductDataList;
import ku.cs.services.MemberDataFileDataSource;
import ku.cs.services.ReportFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class AdminReportController {
    @FXML
    private ListView<ReportProductData> reportListView;


    private MemberDataFileDataSource dataSource;
    private ReportFileDataSource reportFileDataSource;
    private ReportProductDataList reportProductDataList;
    private ReportProductData reportProductData;
    private MemberDatalist memberDatalist;

    private ObservableList<MemberData> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        dataSource = new MemberDataFileDataSource();
        memberDatalist = dataSource.readData();
        reportFileDataSource = new ReportFileDataSource();
        reportProductDataList = reportFileDataSource.readData();
        System.out.println(System.getProperty("user.dir"));
        showListView();

    }

    private void showListView() {
        ArrayList<ReportProductData> member = reportProductDataList.getReportData();
        reportListView.getItems().addAll(member);
    }


    @FXML
    private Button exitButton;

    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void handleUseBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("admin_account");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
