package ShoppingCart.Version1;

public class Product {
    // attributes product ID, product name, quantity, unit price
    String productID, productName;
    byte quantity;
    double unitPrice;

    public Product(){
        productID = "111111";
        productName = "xxxxxx";
        quantity = 0;
        unitPrice = 0;
    }

    public Product(String productID, String productName, byte quantity, double unitPrice){
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
