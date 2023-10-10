package Derived;

import java.util.ArrayList;
import java.util.HashMap;

import Derived.items.GamingConsole;
import Derived.items.Mobile;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Seller_3 extends Seller {
    private Platform thePlatform;
    HashMap<Product, Integer> quantityMapping = new HashMap<Product, Integer>();

    public Seller_3(String id) {

        super(id);

    }

    @Override
    public void addPlatform(Platform thePlatform) { // a seller is connected to a platform via the addPlatform method.

        // We assume that the seller already has a fixed amount of products. following
        // are the products along with their quantities.

        GamingConsole firstConsole = new GamingConsole();
        firstConsole.setArgs("PlayStation_5", "seller3" + "-" + "PlayStation_5", 39000, 43, 128);
        quantityMapping.put(firstConsole, firstConsole.getQuantity()); // mapping the product and quantity

        GamingConsole secondConsole = new GamingConsole();
        secondConsole.setArgs("XBOX_One", "seller3" + "-" + "XBOX_One", 4400, 22, 128);
        quantityMapping.put(secondConsole, secondConsole.getQuantity()); // mapping the product and quantity

        Mobile firstMobile = new Mobile();
        firstMobile.setArgs("Nokia_3310", 111, 999, "seller3" + "-" + "Nokia_3310");
        quantityMapping.put(firstMobile, firstMobile.getQuantity()); // mapping the product and quantity

        Mobile secondMobile = new Mobile();
        secondMobile.setArgs("IPhone_11", 100, 1500, "seller3" + "-" + "IPhone_11");
        quantityMapping.put(secondMobile, secondMobile.getQuantity()); // mapping the product and quantity
        this.thePlatform = thePlatform;

    }

    @Override // method to return an arrayList of all products of a particular category
              // available to Seller_1.
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> Categoryproducts = new ArrayList<Product>();
        int size = 0;
        for (Product product : quantityMapping.keySet()) { // looping through all the products and adding the ones of
                                                           // category passed.
            if (product.getCategory() == whichOne) {
                Categoryproducts.add(product);
                size++;
            }
        }
        if (size == 0) {
            Categoryproducts.add(null);
        }
        return Categoryproducts;
    }

    @Override // method to buy a product from the seller.
    public boolean buyProduct(String productID, int quantity) {
        for (Product product : quantityMapping.keySet()) {
            if (product.getProductID().equals(productID) && quantityMapping.get(product) >= quantity) {
                quantityMapping.put(product, quantityMapping.get(product) - quantity);
                return true;
            }
        }
        return false;
    }
}
