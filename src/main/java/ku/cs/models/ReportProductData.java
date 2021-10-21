package ku.cs.models;

public class ReportProductData {
    private String reportText;
    private String usernameShop;
    private String product;
    private String reportType;
    private String usernameCustomer;

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getUsernameShop() {
        return usernameShop;
    }

    public void setUsernameShop(String usernameShop) {
        this.usernameShop = usernameShop;
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


    public ReportProductData(String usernameShop,  String usernameCustomer, String product, String reportType, String reportText) {
        this.product = product;
        this.usernameShop = usernameShop;
        this.usernameCustomer = usernameCustomer;
        this.reportText = reportText;
        this.reportType = reportType;
    }

    public String toCSV() {
        return  usernameShop + "," + usernameCustomer + "," + product + ","+reportType+ "," + reportText;
    }

    @Override
    public String toString() {
        return "username: " + usernameShop +"\n"
                + "reporter: " + usernameCustomer + "\n"
                + "product: " + product + "\n"
                + "reason: " + reportType + "\n"
                + "note: " + reportText;
    }

}



