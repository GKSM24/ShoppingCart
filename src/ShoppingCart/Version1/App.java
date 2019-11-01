package ShoppingCart.Version1;
import java.util.*;

public class App {
    ProductCatalog products;
    // users array.
    public App(){
        products = new ProductCatalog();
    }

    public static void main(String[] args){
        App app =new App();
        app.addItems();
        app.startApp();
    }

    private void addItems(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter the number of products your website can hold:");
            int numberOfProducts = sc.nextInt(), number = 1;
            sc.nextLine();

            while (number <= numberOfProducts) {
                System.out.println("Enter the product " + number + " info:");
                System.out.println("Enter the product ID:");
                String pid = sc.nextLine();
                System.out.println("Enter the product name:");
                String name = sc.nextLine();
                System.out.println("Enter the product quantity:");
                byte quant = sc.nextByte();
                System.out.println("Enter the product price:");
                double price = sc.nextDouble();
                sc.nextLine();
                products.addProductToCatalog(new Product(pid, name, quant, price));
                number++;
            }

            System.out.println("\n****PRODUCTS LIST****");
            products.showCatalog();
        }
        catch (Exception e){ e.printStackTrace();}
    }


    private void startApp() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("\nEnter the user name:");
            String username = sc.nextLine();
            User user = new User(username);
            do {
                System.out.println("\n1.add item to cart\n"+"2.remove item from cart\n"+"3.show your cart items\n"+"4.print your bill\n"+"5.show products in the website\n"+"6.exit shop");
                System.out.println("Select your option:");
                byte choice = sc.nextByte();
                sc.nextLine();
                String pname;
                switch(choice) {
                    case 1:
                        System.out.println("\nEnter the product name to add:");
                        pname = sc.nextLine();
                        user.addToCart(products.searchProductFromCatalog(pname));
                        break;
                    case 2:
                        if (user.cart.productList.isEmpty()){
                            System.out.println("add items in the cart.");
                        }
                        else {
                            System.out.println("\nEnter the product name to remove:");
                            pname = sc.nextLine();
                            user.removeFromCart(products.searchProductFromCatalog(pname));
                        }
                        break;
                    case 3:
                        if (user.cart.productList.isEmpty()){
                            System.out.println("cart empty");
                        }
                        else {
                            user.showCart();
                        }
                        break;
                    case 4:
                        if (user.cart.productList.isEmpty()){
                            System.out.println("add items in the cart.");
                        }
                        else {
                            System.out.println("Enter your coupon code if you have any.");
                            user.coupon = sc.nextLine();
                            user.printInvoice();
                            for (Product product : user.cart.productList.keySet())
                                product.quantity -= user.cart.productList.get(product);
                            user.cart.productList.clear();
                        }
                        break;
                    case 5:
                        products.showCatalog();
                        break;
                    case 6:
                        System.exit(0);
                }
            }while (true);
        } catch(Exception e){e.printStackTrace();}
    }
}
