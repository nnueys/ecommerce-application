package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ku.cs.fxrouter.FXRouter;
import java.io.IOException;


/**
 * JavaFX App
 */

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "nuey", 800, 600);
        configRoute();
        stage.initStyle(StageStyle.UNDECORATED);
        FXRouter.goTo("login_page");
    }

    private void configRoute() {
        String packageStr = "ku/cs/";
        //nuey
        FXRouter.when("login_page", packageStr+"login_page.fxml");
        FXRouter.when("register_page", packageStr+"register_page.fxml");
        FXRouter.when("my_profile_page", packageStr+"my_profile_page.fxml");
        FXRouter.when("change_password", packageStr+"change_password.fxml");
        FXRouter.when("admin_login_page", packageStr+"admin_login_page.fxml");
        FXRouter.when("application_advice", packageStr+"application_advice.fxml");

        //cake
        FXRouter.when("admin_account", packageStr+"admin_account.fxml");
        FXRouter.when("admin_member", packageStr+"admin_member.fxml");
        FXRouter.when("admin_report", packageStr+"admin_report.fxml");
        FXRouter.when("member_project", packageStr+"member_project.fxml");

        //dew
        FXRouter.when("open_shop", packageStr+"open_shop.fxml");
        FXRouter.when("product_list", packageStr+"my_store.fxml");
        FXRouter.when("add_product", packageStr+"add_product.fxml");
        FXRouter.when("confirm_add_product", packageStr+"confirm_add_product.fxml");
        FXRouter.when("product_grid", packageStr+"product_grid_in_my_store.fxml");
        FXRouter.when("product_data_for_seller", packageStr+"product_data_for_seller.fxml");
        FXRouter.when("edit_product", packageStr+"edit_product.fxml");
        FXRouter.when("confirm_edit_product", packageStr+"confirm_edit_quantity.fxml");
        FXRouter.when("confirm_edit_image_product", packageStr+"confirm_edit_product.fxml");
        FXRouter.when("shop_setting", packageStr+"shop_setting.fxml");
        FXRouter.when("new_order", packageStr+"new_order.fxml");
        FXRouter.when("order_product_grid", packageStr+"new_order_grid.fxml");
        FXRouter.when("tracking_number", packageStr+"tracking_number.fxml");
        FXRouter.when("order_delivered", packageStr+"shipping_order.fxml");
        FXRouter.when("shipping_product_grid", packageStr+"shipping_product_grid.fxml");

        //rose
        FXRouter.when("market_place", packageStr+"market_place.fxml");
        FXRouter.when("order_already", packageStr+"finish_ordering.fxml");
        FXRouter.when("order", packageStr+"order.fxml");
        FXRouter.when("order_list", packageStr+"my_purchase.fxml");
        FXRouter.when("report_product", packageStr+"report_product.fxml");
        FXRouter.when("review_product", packageStr+"review_product.fxml");
        FXRouter.when("confirm_order", packageStr+"confirm_order.fxml");
        FXRouter.when("market_shop", packageStr+"each_shop.fxml");
        FXRouter.when("product_market_shop_grid", packageStr+"product_grid_in_each_shop.fxml");
        FXRouter.when("product_market_grid", packageStr+"product_grid_in_market_place.fxml");
        FXRouter.when("report_review_product", packageStr+"report_review_product.fxml");

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}