package ku.cs.models;

import java.util.ArrayList;

public class OrderDataList {
    private ArrayList<OrderData> newOrderProductData;

    public OrderDataList() {
        newOrderProductData = new ArrayList<>();
    }

    public void addOrderProduct(OrderData data) {
        this.newOrderProductData.add(data);
    }

    public ArrayList<OrderData> getNewOrderProductData() {
        return this.newOrderProductData;
    }


    public int countData() {
        return this.newOrderProductData.size();
    }


    public String toCSV() {
        String result = "";
        for (OrderData data : this.newOrderProductData) {
            result += data.toCSV() + "\n";
        }
        return result;
    }


    public String recordTrackingNumber(String usernameCustomer, String usernameShop, String nameProduct, double priceProduct, int orderProductOrder, String imagePath, String trackingNumber, String newTrackingOrder){
        for (OrderData orderData : newOrderProductData) {
            if (orderData.getUsernameCustomer().equals(usernameCustomer) && orderData.getNameProduct().equals(nameProduct) && orderData.getShopName().equals(usernameShop) &&orderData.getPriceProduct()==priceProduct && orderData.getOrderProductOrder()==orderProductOrder && orderData.getTrackingNumber().equals("-")) {
                orderData.setTrackingNumber(newTrackingOrder);
                return "pass";
            }
        }
        return "false";
    }



}
