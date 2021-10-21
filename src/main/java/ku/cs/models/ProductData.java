package ku.cs.models;


public class ProductData {
    private String username;
    private String shopName;
    private String nameProduct;
    private String descriptionProduct;
    private double priceProduct;
    private int quantityProduct;
    private String imagePath;
    private String newProductName;
    private double newProductPrice;



    public ProductData(String username, String shopName, String nameProduct, String descriptionProduct, double priceProduct, int quantityProduct, String imagePath) {
        this.username = username;
        this.shopName = shopName;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct = quantityProduct;
        this.imagePath = imagePath;
    }

    public ProductData(String username, String shopName, String nameProduct, String descriptionProduct, double priceProduct, int quantityProduct, String imagePath, String newProductName, double newProductPrice) {
        this.username = username;
        this.shopName = shopName;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.quantityProduct = quantityProduct;
        this.imagePath = imagePath;
        this.newProductName = newProductName;
        this.newProductPrice = newProductPrice;
    }

    public String getNewProductName() {
        return newProductName;
    }

    public void setNewProductName(String newProductName) {
        this.newProductName = newProductName;
    }

    public double getNewProductPrice() {
        return newProductPrice;
    }

    public void setNewProductPrice(double newProductPrice) {
        this.newProductPrice = newProductPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getShopName() {
        return shopName;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public void addProductQuantity(int quantity) {
        if(quantity > 0){
            this.quantityProduct += quantity;
        }
    }

    public void minusFromBuyProduct(int quantityOrder) {
        if(quantityOrder > 0){
            this.quantityProduct -= quantityOrder;
        }
    }

    public String limitAmount(int quantityProduct, int limitProduct) {
        if (quantityProduct <= limitProduct) {
            return "red";
        }
        return "black";
    }


    public String toCSV() {
        return  "seller," +
                username + "," +
                shopName + "," +
                nameProduct + "," +
                descriptionProduct + "," +
                priceProduct + "," +
                quantityProduct + "," +
                imagePath;

    }

}
