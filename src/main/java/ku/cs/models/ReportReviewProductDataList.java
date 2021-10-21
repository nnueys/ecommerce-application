package ku.cs.models;

import java.util.ArrayList;

public class ReportReviewProductDataList {
    private ArrayList<ReportReviewProductData> reportReview;

    public ReportReviewProductDataList() {
        reportReview = new ArrayList<>();
    }

    public void addReportReviewProductData(ReportReviewProductData data) {
        this.reportReview.add(data);
    }

    public ArrayList<ReportReviewProductData> getReportData() {
        return this.reportReview;
    }

    public ArrayList<ReportReviewProductData> getMemberData() {
        return this.reportReview;
    }

    public int countData() {
        return this.reportReview.size();
    }

    public String toCSV() {
        String result = "";
        for (ReportReviewProductData data : this.reportReview) {
            result += data.toCSV() + "\n";
        }
        return result;
    }
}
