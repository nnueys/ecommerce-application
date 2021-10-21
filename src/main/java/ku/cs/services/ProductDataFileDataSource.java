package ku.cs.services;

import ku.cs.models.ProductData;
import ku.cs.models.ProductDatalist;

import java.io.*;

public class ProductDataFileDataSource implements DataSource<ProductDatalist>{
    private String directoryName;
    private String filename;

    public ProductDataFileDataSource() {
        this("Product", "Product.csv");
    }

    public ProductDataFileDataSource(String directoryName, String filename) { // test use
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
    public ProductDatalist readData() {
        ProductDatalist productDatalist = new ProductDatalist();
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

                productDatalist.addProduct(new ProductData(
                        data[1].trim(), // username
                        data[2].trim(), // shopName
                        data[3].trim(), // nameProduct
                        data[4].trim(), // descriptionProduct
                        Double.parseDouble(data[5].trim()), // priceProduct
                        Integer.parseInt(data[6].trim()), // quantityProduct
                        data[7].trim() //imagePath
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
            return productDatalist;
        }
    }

    @Override
    public void writeData(ProductDatalist productDatalist) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(productDatalist.toCSV());
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
