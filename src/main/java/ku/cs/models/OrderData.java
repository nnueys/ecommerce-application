package ku.cs.models;

public class OrderData {
    private String usernameCustomer;
    private String shopName;
    private String nameProduct;
    private double priceProduct;
    private int orderProductOrder;
    private String imagePath;
    private String trackingNumber;


//    public OrderData(String usernameCustomer, String nameProduct, double priceProduct, int orderProductOrder, String imagePath) {
//        this.nameProduct = nameProduct;
//        this.priceProduct = priceProduct;
//        this.orderProductOrder = orderProductOrder;
//        this.usernameCustomer = usernameCustomer;
//        this.imagePath = imagePath;
//    }
//
//    public OrderData(String usernameCustomer, String usernameShop,  String nameProduct, double priceProduct, int orderProductOrder, String imagePath) {
//        this.usernameCustomer = usernameCustomer;
//        this.usernameShop = usernameShop;
//        this.nameProduct = nameProduct;
//        this.priceProduct = priceProduct;
//        this.orderProductOrder = orderProductOrder;
//        this.imagePath = imagePath;
//    }

    public OrderData(String usernameCustomer, String shopName, String nameProduct, double priceProduct, int orderProductOrder, String imagePath, String trackingNumber) {
        this.usernameCustomer = usernameCustomer;
        this.shopName = shopName;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.orderProductOrder = orderProductOrder;
        this.imagePath = imagePath;
        this.trackingNumber = trackingNumber;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setOrderProductOrder(int orderProductOrder) {
        this.orderProductOrder = orderProductOrder;
    }

    public String getUsernameCustomer() {
        return usernameCustomer;
    }

    public void setUsernameCustomer(String usernameCustomer) {
        this.usernameCustomer = usernameCustomer;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getOrderProductOrder(){
        return orderProductOrder;
    }

    public void setOrderProductOrder(){
        this.orderProductOrder = orderProductOrder;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public double getTotalPriceOrder() {
        double total = priceProduct * orderProductOrder;

        return total;
    }

    public String toCSV() {
        return usernameCustomer + "," +
                shopName + "," +
                nameProduct + "," +
                priceProduct + "," +
                orderProductOrder+ "," +
                imagePath + "," +
                trackingNumber;
    }
    @Override
    public String toString() {
        return  "name product : " + nameProduct + "\n" + "price : " + priceProduct  + " baht" + "\n" + "tracking number : " + trackingNumber;
    }
}
