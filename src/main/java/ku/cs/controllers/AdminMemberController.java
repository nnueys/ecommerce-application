package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.AdminData;
import ku.cs.models.MemberData;
import ku.cs.models.MemberDatalist;
import ku.cs.models.ReportProductDataList;
import ku.cs.services.DataSource;
import ku.cs.services.MemberDataFileDataSource;
import ku.cs.services.MemberDataSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class AdminMemberController {

    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private ListView memberListView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label shopNameLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView profilePictureImageView;
    @FXML
    private Button banButton;
    @FXML
    private Label tryToLoginLabel;
    @FXML
    private Label logInLabel;



    private MemberDataFileDataSource dataSource;
    private MemberDatalist memberDatalist;
    private ReportProductDataList reportProductDataList;
    private MemberData selectedMemberData;
    DataSource<MemberDatalist> memberDatalistDataSource;
    DataSource<ReportProductDataList> reportProductDataListDataSource;
    private Comparator<MemberData> memberDataComparator = new MemberDataSort();
    private ObservableList<MemberData> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        memberDatalistDataSource = new MemberDataFileDataSource();
        this.memberDatalist = memberDatalistDataSource.readData();
        System.out.println(System.getProperty("user.dir"));
//        memberDatalist.sort(memberDataComparator);
//        Arrays.sort(memberDatalist, memberDataComparator);
        showListView();
        clearSelectedMemberCard();
        handleSelectedListView();
    }

    private void showListView() {
        ArrayList<MemberData> member = memberDatalist.getMemberData();
        memberListView.getItems().addAll(memberDatalist.sort(removeAdmin()));
//        data.addAll(memberDatalist.sort());
//        memberListView.setItems(data);
//        memberListView.getItems().addAll(data);
//        memberListView.refresh();
    }

    private void handleSelectedListView() {
        memberListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<MemberData>() {
                    @Override
                    public void changed(ObservableValue<? extends MemberData> observable,
                                        MemberData oldValue, MemberData newValue) {
                        System.out.println(newValue + " is selected");
                        System.out.println(newValue.isUserBan());
                        showSelectedMemberData(newValue);
                    }
                });
    }

    private void showSelectedMemberData(MemberData member) {
        this.selectedMemberData = member;
        nameLabel.setText(member.getName());
        usernameLabel.setText(member.getUsername());
        profilePictureImageView.setImage(new Image("file:"+member.getImagePath(), true));
        timeLabel.setText(member.getLocalDateTime());

        if (member.isUserBan()) {
            tryToLoginLabel.setText("ACCESS TIME     :");
            logInLabel.setText(String.valueOf(member.getTryToLogin()));
            banButton.setText("UNBAN");
        }
        else {
            tryToLoginLabel.setText("");
            logInLabel.setText("");
            banButton.setText("BAN");
        }

        if (member.getLocalDateTime().equals("null")){
            timeLabel.setText("-");
        }

        if (member.getShopName().equals("null")) {
            shopNameLabel.setText("-");
        }
        else {
            shopNameLabel.setText(member.getShopName());
        }
    }

    private void clearSelectedMemberCard() {
        nameLabel.setText("");
        usernameLabel.setText("");
        shopNameLabel.setText("");
        profilePictureImageView.setImage(new Image("file:"+"images/default_pfp.JPG", true));
    }

    @FXML
    public void handleUseNextButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("admin_report");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_report ไม่ได้");
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
            FXRouter.goTo("admin_account");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin_account ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public ArrayList<MemberData> removeAdmin(){
        ArrayList<MemberData> member = new ArrayList<>();
        for (MemberData memberData : memberDatalist.getMemberData() ) {
            if (!(memberData instanceof AdminData)) {
                member.add(memberData);
            }
        }
        return member;
    }

    public void userBan(ActionEvent actionEvent) {
        if (selectedMemberData.equals(null)) return;
        selectedMemberData.forUserBan();
        memberDatalistDataSource.writeData(memberDatalist);
        memberListView.refresh();
        showSelectedMemberData(selectedMemberData);

    }

}