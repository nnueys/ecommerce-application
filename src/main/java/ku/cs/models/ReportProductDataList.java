package ku.cs.models;

import java.util.ArrayList;

public class ReportProductDataList {
    private ArrayList<ReportProductData> reports;

    public ReportProductDataList() {
        reports = new ArrayList<>();
    }

    public void addReportProduct(ReportProductData data) {
        this.reports.add(data);
    }

    public ArrayList<ReportProductData> getReportData() {
        return this.reports;
    }

    public ArrayList<ReportProductData> getMemberData() {
        return this.reports;
    }

    public int countData() {
        return this.reports.size();
    }

    public String toCSV() {
        String result = "";
        for (ReportProductData data : this.reports) {
            result += data.toCSV() + "\n";
        }
        return result;
    }
}
