package ku.cs.services;

import ku.cs.models.ReportProductData;
import ku.cs.models.ReportProductDataList;

import java.io.*;

public class ReportFileDataSource implements DataSource<ReportProductDataList> {
    private String directoryName;
    private String filename;

    public ReportFileDataSource() {
        this("Report", "Report.csv");
    }


    public ReportFileDataSource(String directoryName, String filename) { // test use
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }

    private void initialFileIfNotExist() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = directoryName + File.separator + filename;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public ReportProductDataList readData() {
        ReportProductDataList reportProductDataList = new ReportProductDataList();
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                reportProductDataList.addReportProduct(new ReportProductData(
                        data[0].trim(), //usernameShop
                        data[1].trim(),//usernameCos
                        data[2].trim(),//nameProduct
                        data[3].trim(),//reportType
                        data[4].trim()//reportText

                ));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reportProductDataList;
        }


    }


    @Override
    public void writeData(ReportProductDataList reportProductDataList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(reportProductDataList.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
