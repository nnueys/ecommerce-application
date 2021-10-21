package ku.cs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ku.cs.fxrouter.FXRouter;
import ku.cs.models.MemberData;
import ku.cs.models.PriceRangeFilterer;
import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;
import ku.cs.services.DataSource;
import ku.cs.services.ProductDataFileDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class MarketPlaceController {


    @FXML
    private Button exitButton;

    @FXML
    private ScrollPane marketContainer;

    @FXML
    private GridPane productMarketGrid;

    @FXML
    private TextField priceRangeTextField1;
    @FXML
    private TextField priceRangeTextField2;
    @FXML
    private Label notificationLabel;


    private ProductDatalist productDatalist;
    private ArrayList<ProductData> productItem;
    private PriceRangeFilterer priceRangeFilterer;


    private MemberData memberData = (MemberData) FXRouter.getData();

    private  double PriceRange1 ;
    private  double PriceRange2 ;

    @FXML
    public void initialize() {
        System.out.println("initialize MarketPlace");

        DataSource<ProductDatalist> dataSource;
        String directory = "Product";
        String filename = "Product.csv";
        dataSource = new ProductDataFileDataSource(directory, filename);
        this.productDatalist = dataSource.readData();
        this.productItem = productDatalist.getProductData();

        PriceRange1 = 0;
        PriceRange2 = 0;
        productAll();

    }




    public void getPrice(){
        if (priceRangeTextField1.getText().equals("") || priceRangeTextField2.getText().equals("")) {
            ShowProduct();
            notificationLabel.setText("please enter price range");
        }
        else{
            DataSource<ProductDatalist> dataSource;
                String directory = "Product";
                String filename = "Product.csv";
                dataSource = new ProductDataFileDataSource(directory, filename);
                    this.productDatalist = dataSource.readData();
                    this.productItem = productDatalist.getProductData();


                PriceRange1 = 0;
                PriceRange2 = 0;
            productMarketGrid.getChildren().clear();
            PriceRange1 = Double.parseDouble(priceRangeTextField1.getText());
            PriceRange2 = Double.parseDouble(priceRangeTextField2.getText());

            System.out.println(PriceRange1);
            System.out.println(PriceRange2);
            int column = 0;
            int row = 2;
            try {
                productDatalist = priceRange(productDatalist, PriceRange1, PriceRange2);

                for (ProductData productData : productDatalist.getProductData()) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/ku/cs/product_grid_in_market_place.fxml"));

                    AnchorPane anchorPane = fxmlLoader.load();

                    ProductGridInMarketplaceController productGridInMarketplaceController = fxmlLoader.getController();
                    productGridInMarketplaceController.showProductData(productData);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    productMarketGrid.add(anchorPane, column++, row);  //(child,column,row)
                    //ตั้งค่าของที่จะขาย
                    productMarketGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    productMarketGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    productMarketGrid.setMaxWidth(Region.USE_PREF_SIZE);


                    productMarketGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    productMarketGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    productMarketGrid.setMaxHeight(Region.USE_PREF_SIZE);


                    //ระยะห่างของ product แต่ละชิ้น
                    GridPane.setMargin(anchorPane, new Insets(13));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    public void handleToProfile(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("my_profile_page",memberData);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ไปที่หน้า profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void handleExitButton(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public ProductDatalist priceRange(ProductDatalist productDatalist,  double PriceRange1 , double PriceRange2) {

        ProductDatalist filtered = productDatalist.filter(new PriceRangeFilterer(PriceRange1, PriceRange2));

        return filtered;

    }

    public void ShowProduct() {
        int column = 0;
        int row = 2;
        try {
            productMarketGrid.getChildren().clear();
            for (ProductData productData : productItem) {


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ku/cs/product_grid_in_market_place.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ProductGridInMarketplaceController productGridInMarketplaceController = fxmlLoader.getController();
                productGridInMarketplaceController.showProductData(productData);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                productMarketGrid.add(anchorPane, column++, row);  //(child,column,row)
                //ตั้งค่าของที่จะขาย
                productMarketGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                productMarketGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                productMarketGrid.setMaxWidth(Region.USE_PREF_SIZE);


                productMarketGrid.setMaxHeight(Region.USE_COMPUTED_SIZE);
                productMarketGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                productMarketGrid.setMaxHeight(Region.USE_PREF_SIZE);


                //ระยะห่างของ product แต่ละชิ้น
                GridPane.setMargin(anchorPane, new Insets(13));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void minToMax(ActionEvent actionEvent) {
        Comparator<ProductData> productDataComparator = new Comparator<ProductData>() {
            @Override
            public int compare(ProductData o1, ProductData o2) {
                if (o1.getPriceProduct() > o2.getPriceProduct()) return 1;
                if (o1.getPriceProduct() < o2.getPriceProduct()) return -1;
                return 0;
            }
        };
        productItem.sort(productDataComparator);
        ShowProduct();
    }


    @FXML
    public void maxToMin(ActionEvent actionEvent) {
        Comparator<ProductData> productDataComparator = new Comparator<ProductData>() {
            @Override
            public int compare(ProductData o1, ProductData o2) {
                if (o1.getPriceProduct() > o2.getPriceProduct()) return -1;
                if (o1.getPriceProduct() < o2.getPriceProduct()) return 1;
                return 0;
            }
        };
        productItem.sort(productDataComparator);
        ShowProduct();

    }

    public void productAll() {
        productDatalist.reverse(productItem);
        ShowProduct();

    }

}


