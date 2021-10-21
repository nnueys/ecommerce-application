package ku.cs.services;

import ku.cs.models.ProductData;

public interface Filterer {
    boolean filter(ProductData productData);
}
