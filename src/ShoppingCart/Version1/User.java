package ShoppingCart.Version1;
import java.util.*;

public class User{

    //attributes userName, Cart and Coupon.
    String userName;
    Cart cart;
    String coupon;
    Scanner sc = new Scanner(System.in);
    public User(){
        //default constructor
    }

    public User(String userName){
        this.userName = userName;
        this.cart = new Cart();
        this.coupon = "";
    }

    public void addToCart(Product product){
        if (product != null) {
            System.out.println("Enter the quantity you require:");
            int quantity = sc.nextInt();
            sc.nextLine();
            if (cart.productList.containsKey(product)) {
                int previousQuantity = cart.productList.get(product);
                if ((previousQuantity + quantity) <= product.quantity)
                    cart.productList.put(product, previousQuantity + quantity);
            } else {
                if (quantity <= product.quantity)
                    cart.productList.put(product, quantity);
                else{
                    System.out.println("Please enter in low quantity...");
                }
            }
        }
    }

    public void removeFromCart(Product product){
        if (cart.productList.containsKey(product))
            cart.productList.remove(product);
        else
            System.out.println("Product is not added to remove.");
    }

    public void showCart(){
        System.out.println("\nYour items");
        for (Product product:cart.productList.keySet())
            System.out.printf("%-10s\t% 3d% 10.2f\n",product.productName,cart.productList.get(product),(product.unitPrice * cart.productList.get(product)));
    }

    private double getTotalAmount(){
        double amount= 0;
        for (Product product:cart.productList.keySet())
            amount += (product.unitPrice*cart.productList.get(product));
        return amount;
    }

    private double getPayableAmount(){
      double afterDiscountAmount = getTotalAmount()-applyCoupon(coupon);
      double tax = afterDiscountAmount*0.15;
      return afterDiscountAmount+tax;
    }

    private double applyCoupon(String couponCode){
        try {
            couponCode = couponCode.toUpperCase();
            if (couponCode.equals(Coupon.coupon10))
                return (getTotalAmount() * 0.1);
            else if (couponCode.equals(Coupon.coupon20))
                return (getTotalAmount() * 0.2);
            else if (couponCode.equals(Coupon.coupon30))
                return (getTotalAmount() * 0.3);
            else if (couponCode.equals(Coupon.coupon50))
                return (getTotalAmount() * 0.5);
            else
                return 0;
        }
        catch(Exception e){ System.out.println("The coupon code is not given..."); }
        return 0;
    }

    public void printInvoice(){
            System.out.println("\n    ******BILL******     ");
            System.out.printf("%-10s%8s%10s%10s\n", "ITEM", "QUANT", "U.PRICE", "T.PRICE");
            for (Product product : cart.productList.keySet())
                System.out.printf("%-10s\t% 3d%10.2f% 10.2f\n",product.productName,cart.productList.get(product),product.unitPrice,(product.unitPrice * cart.productList.get(product)));
            if (applyCoupon(coupon) > 0)
                System.out.println("Coupon applied discount:" + applyCoupon(coupon));
            else
                System.out.println("Invalid Coupon applied. No discount");
            System.out.println("Your Bill amount:"+getPayableAmount());
    }
}
