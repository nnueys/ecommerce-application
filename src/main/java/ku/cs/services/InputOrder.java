package ku.cs.services;

import ku.cs.models.OrderData;
import ku.cs.models.OrderDataList;


public class InputOrder implements DataSource<OrderDataList> {
    private String usernameCustomer;
    private String usernameShop;
    private String nameProduct;
    private double priceProduct;
    private int orderProductOrder;
    private String imagePath;
    private String trackingNumber;
    private OrderDataList orderDataList;
    private OrderData orderData;


    public String orderData(String usernameCustomer ,String usernameShop,String nameProduct, double priceProduct, int orderProductOrder,String imagePath, String trackingNumber) {
        DataSource<OrderDataList> dataSource;
        String directory = "Order";
        String filename = "Order.csv";

        dataSource = new OrderDataFileDataSource(directory, filename);
        OrderDataList orderDataList = dataSource.readData();

        orderDataList.addOrderProduct(new OrderData(usernameCustomer , usernameShop, nameProduct, priceProduct, orderProductOrder,imagePath,trackingNumber));
        dataSource.writeData(orderDataList);
        return "pass";


    }


    @Override
    public OrderDataList readData() {
        return orderDataList;
    }

    @Override
    public void writeData(OrderDataList orderDataList) {
        this.orderDataList = orderDataList;
    }
}










