package ku.cs.services;

import ku.cs.models.ReviewProductData;
import ku.cs.models.ReviewProductDataList;

public class InputReviewProduct implements DataSource<ReviewProductDataList>{
    private double star;


    private String reviewText;
    private String username;
    private String nameProduct;

    private ReviewProductDataList reviewProductDataList;
    private ReviewProductData reviewProductData;


    public String reviewProductData(String username,String nameProduct,String nameShop,double star,String reviewText ){
        DataSource<ReviewProductDataList> dataSource;
        String directory = "ReviewProduct";
        String filename = "ReviewProduct.csv";

        dataSource = new ReviewProductFileDataSource(directory, filename);
        ReviewProductDataList reviewProductDataList = dataSource.readData();

        reviewProductDataList.addReviewProduct(new ReviewProductData(username, nameProduct,  nameShop,star, reviewText) );
        dataSource.writeData(reviewProductDataList);
        return "pass";

    }


    @Override
    public ReviewProductDataList readData() {
        return null;
    }

    @Override
    public void writeData(ReviewProductDataList reviewProductDataList) {

    }
}
