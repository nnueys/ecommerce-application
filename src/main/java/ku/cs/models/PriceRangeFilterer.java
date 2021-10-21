package ku.cs.models;


import ku.cs.services.Filterer;

public class PriceRangeFilterer implements Filterer {
    private double priceRangeMax;
    private double priceRangeMin;


    public PriceRangeFilterer(double priceRangeMin, double priceRangeMax){
        this.priceRangeMin = priceRangeMin;
        this.priceRangeMax = priceRangeMax;
    }


    @Override
    public boolean filter(ProductData productData) {
        double price = productData.getPriceProduct();
        if (price >= priceRangeMin && price <= priceRangeMax){
            return true;
        }
        System.out.println(priceRangeMin);
        System.out.println(priceRangeMax);
        System.out.println("null");
        return false;
    }


}
