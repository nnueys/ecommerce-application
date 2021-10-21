package ku.cs.services;

import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;

public class InputProduct implements DataSource<ProductDatalist>{

    private String username;
    private String shopName;
    private String nameProduct;
    private String descriptionProduct;
    private double priceProduct;
    private int quantityProduct;

    private ProductDatalist productDatalist;
    private ProductData productData;

    public String productData(String username, String shopName, String nameProduct ,String descriptionProduct, double priceProduct, int quantityProduct, String imagePath) {

        DataSource<ProductDatalist> dataSource;
        String directory = "product";
        String filename = "Product.csv";

        dataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = dataSource.readData();

        if (productDatalist.checkNameProduct(shopName, nameProduct)) {
            return "Product Name Has Already Used";
        }

        productDatalist.addProduct(new ProductData(username, shopName, nameProduct ,descriptionProduct, priceProduct, quantityProduct, imagePath));
        dataSource.writeData(productDatalist);
        return "pass";

    }

    public boolean nameProductCheck(String shopName, String nameProduct) {
        DataSource<ProductDatalist> dataSource;
        String directory = "product";
        String filename = "product.csv";

        dataSource = new ProductDataFileDataSource(directory, filename);
        ProductDatalist productDatalist = dataSource.readData();

        for(ProductData product: productDatalist.getProductData() ) {
            if (product.getShopName().equals(shopName)) {
                if (product.getNameProduct().equals(nameProduct)) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ProductDatalist readData() {
        return productDatalist;
    }

    @Override
    public void writeData(ProductDatalist productDatalist) {
        this.productDatalist = productDatalist;
    }

}
