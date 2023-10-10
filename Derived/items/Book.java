package Derived.items;

import ecomm.*;

public class Book extends Product {
    //following attributes to the Book class.
    private String author;
    private String name;
    private int quantity;
    private String publisher;
    private String year;
    private String pages;
    private float price;
    private String productID;

    //Method to set values to these arguments.
    public void setArgs(float price, String name, String publisher, String year, String pages, String author,
            int quantity, String productID) {
        this.price = price;
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.author = author;
        this.quantity = quantity;
        this.productID = productID;
    }

    //We find the category of the product by getCategory() which calls the enum Book.
    public Globals.Category getCategory() {
        return Globals.Category.Book;
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
    public String getpages() {
        return pages;
    }
    public String getYear() {
        return year;
    }
    public String getpublisher() {
        return publisher;
    }
    public String getauthor() {
        return author;
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
