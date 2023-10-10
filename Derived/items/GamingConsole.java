package Derived.items;
import ecomm.*;
public class GamingConsole extends Product {
    //following attributes to the GamingConsole class.
    private String name;
    private String ProductID;
    private float price;
    private int quantity;
    private int memory;

    //Method to set values to these arguments.
    public void setArgs(String name,String ProductID,float price,int quantity,int memory){
        this.name=name;
        this.ProductID=ProductID;
        this.price=price;
        this.quantity=quantity;
        this.memory=memory;
    }
    
    //We find the category of the product by getCategory() which calls the enum GamingConsole
    public Globals.Category getCategory() {
        return Globals.Category.GamingConsole;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getProductID() {
        return this.ProductID;
    }

    public Integer getMemory() {
        return this.memory;
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
