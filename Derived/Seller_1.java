package Derived;

import java.util.ArrayList;
import java.util.HashMap;
import Derived.items.Book;
import Derived.items.GamingConsole;
import Derived.items.Mobile;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

public class Seller_1 extends Seller {
    private Platform thePlatform; // The seller keeps track of the Platform to which he is connected to.
    HashMap<Product, Integer> quantityMapping = new HashMap<Product, Integer>(); // A map which maps the Product Name to
                                                                                 // quantity.

    public Seller_1(String id) { // each seller has a particular ID.

        super(id);

    }

    @Override
    public void addPlatform(Platform thePlatform) { // a seller is connected to a platform via the addPlatform method.

        // We assume that the seller already has a fixed amount of products. following
        // are the products along with their quantities.

        Book firstBook = new Book();
        firstBook.setArgs(19, "Harry_Potter_and_the_Philosopher's_Stone", "Bloomsbury", "1999", "3212", "JK Rowling",
                50, "seller1" + "-" + "Harry_Potter_and_the_Philosopher's_Stone");
        quantityMapping.put(firstBook, firstBook.getQuantity()); // mapping the product and quantity.

        Book secondBook = new Book();
        secondBook.setArgs(22, "Fire_&_Blood", "Dougin", "2018", "4228", "George R. R. Martin", 23,
                "seller1" + "-" + "Fire_&_Blood");
        quantityMapping.put(secondBook, secondBook.getQuantity()); // mapping the product and quantity.

        Mobile firstMobile = new Mobile();
        firstMobile.setArgs("POCO_X4_Pro_5G", 7, 999, "seller1" + "-" + "POCO_X4_Pro_5G");
        quantityMapping.put(firstMobile, firstMobile.getQuantity()); // mapping the product and quantity.

        Mobile secondMobile = new Mobile();
        secondMobile.setArgs("IPhone_15", 10, 8999, "seller1" + "-" + "IPhone_15");
        quantityMapping.put(secondMobile, secondMobile.getQuantity()); // mapping the product and quantity.
        Mobile thirdMobile = new Mobile();
        thirdMobile.setArgs("Redimi_Note_Pro_3", 15, 3499, "seller1" + "-" + "Redimi_Note_Pro_3");
        quantityMapping.put(thirdMobile, thirdMobile.getQuantity()); // mapping the product and quantity

        GamingConsole secondConsole = new GamingConsole();
        secondConsole.setArgs("XBOX_Two", "seller1" + "-" + "XBOX_Two", 9400, 12, 328);
        quantityMapping.put(secondConsole, secondConsole.getQuantity()); // mapping the product and quantity
        // setting up the platform
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
