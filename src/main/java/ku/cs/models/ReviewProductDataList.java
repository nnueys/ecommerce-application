package ku.cs.models;

import java.util.ArrayList;

public class ReviewProductDataList {
    private ArrayList<ReviewProductData> newReviewProductData;

    public ReviewProductDataList() {
        newReviewProductData = new ArrayList<>();
    }

    public void addReviewProduct(ReviewProductData data) {
        this.newReviewProductData.add(data);
    }

    public ArrayList<ReviewProductData> newReviewProduct() {
        return this.newReviewProductData;
    }

    public int countData() {
        return this.newReviewProductData.size();
    }


    public String toCSV() {
        String result = "";
        for (ReviewProductData data : this.newReviewProductData) {
            result += data.toCSV() + "\n";
        }
        return result;
    }


    int count = 0;
    double total = 0;
    double average = 0.0;
    public double averageStars(ArrayList<ReviewProductData> productReview) {
        if ( productReview.size() == 0) {
            return 0;
        }else {
            for (ReviewProductData reviewProductData :productReview) {
                total += reviewProductData.getStar();
                count += 1;
            }
            average = total/count;
            return average;
        }
    }

    int countPeople = 0;
    public int peopleTotal(ArrayList<ReviewProductData> productReview) {
        for (ReviewProductData reviewProductData :productReview) {
            countPeople += 1;
        }

        return countPeople;
    }

}
