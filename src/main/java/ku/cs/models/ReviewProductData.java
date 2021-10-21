package ku.cs.models;

public class ReviewProductData {


    private double star;
    private String reviewText;
    private String username;
    private String nameProduct;
    private String shopName;


    public ReviewProductData(String username, String nameProduct, String shopName, double star, String reviewText) {
        this.star = star;
        this.reviewText = reviewText;
        this.username = username;
        this.nameProduct = nameProduct;
        this.shopName =shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }



    public String toCSV() {
        return username + "," +
                nameProduct + "," +
                shopName+","+
                star + "," +
                reviewText ;
    }

    @Override
    public String toString() {
        return  star + "points\n" + "review : " + reviewText;
    }


}
