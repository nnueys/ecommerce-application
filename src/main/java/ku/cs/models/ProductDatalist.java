package ku.cs.models;

import ku.cs.services.Filterer;

import java.util.ArrayList;
import java.util.Collections;

public class ProductDatalist {

    private ArrayList<ProductData> newProductData;

    public ProductDatalist() {
        newProductData = new ArrayList<>();
    }

    public void addProduct(ProductData data) {
        this.newProductData.add(data);
    }

    public ArrayList<ProductData> getProductData() {
        return this.newProductData;
    }

    public ArrayList<ProductData> getAllProducts() {
        return newProductData;
    }

    public int countData() {
        return this.newProductData.size();
    }

    public String toCSV() {
        String result = "";
        for (ProductData data : this.newProductData) {
            result += data.toCSV() + "\n";
        }
        return result;
    }

    public boolean checkNameProduct(String shopName, String nameProduct) {
        for (ProductData productData : newProductData) {
            if (productData.getShopName().equals(shopName) && productData.getNameProduct().equals(nameProduct)) {
                return true;
            }
        }
        return false;
    }

    public String recordEditProduct(String username, String nameProduct , String descriptionProduct ,double priceProduct, String newNameProduct, double newPriceProduct) {
        for (ProductData product : newProductData) {
            if (product.getUsername().equals(username) && product.getDescriptionProduct().equals(descriptionProduct) && product.getNameProduct().equals(nameProduct)) {
                product.setNameProduct(newNameProduct);
                product.setPriceProduct(newPriceProduct);
                return "pass";
            }
        }
        return "false";
    }

    public String recordAddQuantityProduct(String username, String shopName, String nameProduct ,String descriptionProduct, double priceProduct, int quantityProduct){
        for (ProductData product : newProductData) {
            if (product.getUsername().equals(username) && product.getNameProduct().equals(nameProduct)) {
                product.setQuantityProduct(quantityProduct);
                return "pass";
            }
        }
        return "false";
    }
    public String recordBuyProduct(String username, String shopName, String nameProduct ,String descriptionProduct, double priceProduct, int quantityProduct, int newQuantity){
        for (ProductData product : newProductData) {
            if (product.getUsername().equals(username) && product.getNameProduct().equals(nameProduct)) {
                product.setQuantityProduct(newQuantity);
                return "pass";
            }
        }
        return "false";
    }

    public void reverse (ArrayList<ProductData> productDataArrayList) {

        Collections.reverse(newProductData);
    }

   public ProductDatalist filter(Filterer filterer){
        ProductDatalist productDatalist = new ProductDatalist();

       for (ProductData productData: newProductData) {
           if (filterer.filter(productData)){
               productDatalist.addProduct(productData);
           }
       }
        return productDatalist;
   }



}







