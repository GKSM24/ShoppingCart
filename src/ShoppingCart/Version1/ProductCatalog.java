package ShoppingCart.Version1;
import java.util.*;

public class ProductCatalog {
    ArrayList<Product> productCatalog;

    public ProductCatalog(){
        productCatalog = new ArrayList<Product>();
    }

    public void addProductToCatalog(Product product){
        productCatalog.add(product);
    }

    public void showCatalog(){
        for (Product product:productCatalog)
            System.out.println(product);
    }

    public Product searchProductFromCatalog(String productName){
        if (productName.length() > 0) {
            for (Product product : productCatalog)
                if (product.productName.toLowerCase().equals(productName.toLowerCase()))
                    return product;
        }
        System.out.println("product not found!");
        return null;
    }
}
