package ku.cs.services;

import ku.cs.models.ReportProductData;
import ku.cs.models.ReportProductDataList;

public class InputReport implements DataSource<ReportProductDataList>{
    private String reportText;
    private String usernameShop;
    private String product;
    private String reportType;
    private String usernameCustomer;

    private ReportProductDataList reportProductDataList;
    private ReportProductData reportProductData;

    public String reportProductData(String usernameCustomer,String usernameShop,String product,String reportType ,String reportText){
        DataSource<ReportProductDataList> dataSource;
        String directory = "Report";
        String filename = "Report.csv";

        dataSource = new ReportFileDataSource(directory, filename);
        ReportProductDataList reportProductDataList = dataSource.readData();

        reportProductDataList.addReportProduct(new ReportProductData(usernameCustomer, usernameShop, product, reportType , reportText) );
        dataSource.writeData(reportProductDataList);
        return "pass";

    }


    @Override
    public ReportProductDataList readData() {
        return reportProductDataList;
    }

    @Override
    public void writeData(ReportProductDataList reportProductDataList) {
        this.reportProductDataList =reportProductDataList;

    }
}
