package ku.cs.services;


import ku.cs.models.OrderData;
import ku.cs.models.OrderDataList;

import java.io.*;

public class OrderDataFileDataSource implements DataSource<OrderDataList>{
    private String directoryName;
    private String filename;

    public OrderDataFileDataSource() {
        this("Order", "Order.csv");
    }

    public OrderDataFileDataSource(String directoryName, String filename) { // test use
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
    public OrderDataList readData() {
        OrderDataList orderDataList = new OrderDataList();
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

                orderDataList.addOrderProduct(new OrderData(
                        data[0].trim(),// usernameCustomer
                        data[1].trim(),// shopName
                        data[2].trim(),// orderNameProduct
                        Double.parseDouble(data[3].trim()), // priceProduct
                        Integer.parseInt(data[4].trim()), // ProductOrder
                        data[5].trim(), // image path
                        data[6].trim() // trackingNumber
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
            return orderDataList;
        }
    }

    @Override
    public void writeData(OrderDataList orderDataList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(orderDataList.toCSV());
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
