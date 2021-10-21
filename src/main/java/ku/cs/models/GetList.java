package ku.cs.models;

public class GetList {
    private MemberData memberData;
    private ProductData productData;
    private MemberDatalist memberDatalist;
    private ProductDatalist productDatalist;



    private ReviewProductData reviewProductData;
    private ReviewProductDataList reviewProductDataList;
    private OrderData orderData;
    private OrderDataList orderDataList;

    public GetList(MemberData memberData, ProductData productData, MemberDatalist memberDatalist, ProductDatalist productDatalist, OrderData orderData, OrderDataList orderDataList
    , ReviewProductData reviewProductData, ReviewProductDataList reviewProductDataList) {
        this.memberData = memberData;
        this.productData = productData;
        this.memberDatalist = memberDatalist;
        this.productDatalist = productDatalist;
        this.orderData = orderData;
        this.orderDataList = orderDataList;
        this.reviewProductData = reviewProductData;
        this.reviewProductDataList = reviewProductDataList;

    }
    public ReviewProductData getReviewProductData() {
        return reviewProductData;
    }

    public void setReviewProductData(ReviewProductData reviewProductData) {
        this.reviewProductData = reviewProductData;
    }

    public ReviewProductDataList getReviewProductDataList() {
        return reviewProductDataList;
    }

    public void setReviewProductDataList(ReviewProductDataList reviewProductDataList) {
        this.reviewProductDataList = reviewProductDataList;
    }
    public MemberDatalist getMemberDatalist() {
        return memberDatalist;
    }

    public ProductDatalist getProductDatalist() {
        return productDatalist;
    }

    public MemberData getMemberData() {
        return memberData;
    }

    public void setMemberData(MemberData memberData) {
        this.memberData = memberData;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductDatalist(ProductData productData) {
        this.productData = productData;
    }
    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public OrderDataList getOrderDataList() {
        return orderDataList;
    }

    public void setOrderDataList(OrderDataList orderDataList) {
        this.orderDataList = orderDataList;
    }


}
