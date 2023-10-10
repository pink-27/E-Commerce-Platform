import java.util.*;

import Derived.Platform_1;
import Derived.Seller_1;
import Derived.Seller_2;
import Derived.Seller_3;
import ecomm.Platform;
import ecomm.Seller;

//! use {javac *.java Derived/*.java Derived/items/*.java ecomm/*.java} to complie
//! use {java PlatformMain} to run

public class PlatformMain {
    public static void main(String[] args) {
        // Create a new instance of the Platform_1 class
        Platform platform = new Platform_1();

        // Create three new sellers
        Seller s1 = new Seller_1("seller1");
        Seller s2 = new Seller_2("seller2");
        Seller s3 = new Seller_3("seller3");

        // Add the platform to each seller's list of platforms
        s1.addPlatform(platform);
        s2.addPlatform(platform);
        s3.addPlatform(platform);

        // Add the sellers to the platform's list of sellers
        platform.addSeller(s1);
        platform.addSeller(s2);
        platform.addSeller(s3);
        System.out.print("Type Check to process the customer commands\n");
        System.out.print("Type exit to shut down the platform\n");
        // Read user input from the command line

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                // Read the next line of input
                String st = sc.nextLine();

                // If the input is "Check", process requests from the file
                // "PortalToPlatform.txt"
                if (st.equals("Check")) {
                    platform.processRequests();
                } else if (st.equals("exit")) {
                    break;
                } else {
                    System.out.println("Invalid input");

                }
            }
        }
    }
}
