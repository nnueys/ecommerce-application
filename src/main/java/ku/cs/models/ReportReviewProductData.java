package ku.cs.models;

public class ReportReviewProductData {


    private String reportText;
    private String usernameReview;
    private String product;
    private String reportType;
    private String usernameCustomer;


    public ReportReviewProductData(String usernameReview,  String usernameCustomer, String product, String reportType, String reportText) {
        this.product = product;
        this.usernameReview = usernameReview;
        this.usernameCustomer = usernameCustomer;
        this.reportText = reportText;
        this.reportType = reportType;

    }


    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getUsernameReview() {
        return usernameReview;
    }

    public void setUsernameReview(String usernameReview) {
        this.usernameReview = usernameReview;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getUsernameCustomer() {
        return usernameCustomer;
    }

    public void setUsernameCustomer(String usernameCustomer) {
        this.usernameCustomer = usernameCustomer;
    }


    public String toCSV() {
        return  usernameReview + "," + usernameCustomer + "," + product + ","+reportType+ "," + reportText;
    }

    @Override
    public String toString() {
        return "reviewer: " + usernameReview +"\n"
                + "reporter: " + usernameCustomer + "\n"
                + "product: " + product + "\n"
                + "reason: " + reportType + "\n"
                + "note: " + reportText;
    }


}
