package ku.cs.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.OrderData;
import ku.cs.models.ProductData;

public class ShippingProductGridController {

    @FXML private Label nameShipLabel;
    @FXML private Label quantityShipLabel;
    @FXML private  Label totalAmountShipLabel;
    @FXML private Label trackingShipLabel;
    @FXML private ImageView productImageView;

    private ProductData productData;
    private OrderData orderData;
    private MemberData memberData = (MemberData) FXRouter.getData();

    @FXML
    private void initialize() {
        System.out.println("initialize ShippingProductGridController");
    }

    public void showShipData(OrderData orderData) {
        this.orderData = orderData;
        nameShipLabel.setText(orderData.getNameProduct());
        totalAmountShipLabel.setText(String.valueOf(orderData.getPriceProduct()));
        quantityShipLabel.setText(String.valueOf(orderData.getOrderProductOrder()));
        trackingShipLabel.setText(orderData.getTrackingNumber());
        productImageView.setImage(new Image("file:" + orderData.getImagePath(), true));
    }

}
