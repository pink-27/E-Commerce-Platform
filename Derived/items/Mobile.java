package Derived.items;

import ecomm.*;

public class Mobile extends Product {
    //following attributes to the Mobile class.
    private String name;
    private int quantity;
    private float price;
    private String productID;

    //Method to set values to these arguments.
    public void setArgs(String name, int quantity, float price, String productID) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.productID = productID;
    }

    //We find the category of the product by getCategory() which calls the enum Mobile
    public Globals.Category getCategory() {
        return Globals.Category.Mobile;
    }

    //getters
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductID() {
        return productID;
    }

    //buy method which reduces the quantity count by the amount of books bought.
    public boolean buy(int quantity) {
        if (quantity > this.quantity) {
            return false;
        }
        this.quantity -= quantity;
        return true;
    }

}
