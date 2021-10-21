package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.OrderData;
import ku.cs.models.ProductData;

import java.io.IOException;

public class NewOrderGridController {
    @FXML private Label nameOrderLabel;
    @FXML private Label quantityOrderLabel;
    @FXML private Label totalAmountOrderLabel;
    @FXML private ImageView productImageView;

    private ProductData productData;
    private OrderData orderData;

    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private void initialize() {
        System.out.println("initialize NewOrderGridController");
    }

    @FXML
    public void handleTrackingButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("tracking_number",orderData);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า tracking_number ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    public void showOrderData(OrderData orderData) {
        this.orderData = orderData;
        nameOrderLabel.setText(orderData.getNameProduct());
        totalAmountOrderLabel.setText(String.valueOf(orderData.getPriceProduct()));
        quantityOrderLabel.setText(String.valueOf(orderData.getOrderProductOrder()));
        productImageView.setImage(new Image("file:" + orderData.getImagePath(), true));
    }


}
