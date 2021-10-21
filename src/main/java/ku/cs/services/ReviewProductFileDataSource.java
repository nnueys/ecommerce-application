package ku.cs.services;

import ku.cs.models.ReviewProductData;
import ku.cs.models.ReviewProductDataList;

import java.io.*;

public class ReviewProductFileDataSource implements DataSource<ReviewProductDataList> {
    private String directoryName;
    private String filename;

    public ReviewProductFileDataSource() {
        this("ReviewProduct", "ReviewProduct.csv");
    }


    public ReviewProductFileDataSource(String directoryName, String filename) { // test use
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
    public ReviewProductDataList readData() {
        ReviewProductDataList reviewProductDataList = new ReviewProductDataList();
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
                reviewProductDataList.addReviewProduct(new ReviewProductData(
                        data[0].trim(), //username
                        data[1].trim(),//nameProduct
                        data[2].trim(),//nameShop
                        Double.parseDouble(data[3].trim()),//star
                        data[4].trim()//reviewText


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
            return reviewProductDataList;
        }


    }

    @Override
    public void writeData(ReviewProductDataList reviewProductDataList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(reviewProductDataList.toCSV());
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
