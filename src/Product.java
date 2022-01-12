public class Product {
    private String productName;
    private boolean isExist;
    private double price;
    private double priceVIP;
    private int discountPercentage;


    public Product(String productName, double price,int discountPercentage, boolean isExist){
        this.productName = productName;
        this.price = price;
        this.priceVIP = this.price - (this.price /100) * discountPercentage;

        this.discountPercentage = discountPercentage;
        this.isExist = isExist;
    }

    public double getPriceVIP() {
        return priceVIP;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }



    public String toString() {
        return "Name: " + this.productName + "\n" +
                "   Price: " + this.price +"\n" +
                "   Is Exist: " + this.isExist+"\n";
    }
}
