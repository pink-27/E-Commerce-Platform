package Derived;

import java.util.*;

import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals.Category;

//import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter; // Import the FileWriter class

public class Platform_1 extends Platform {
    // A list of sellers on the platform
    public ArrayList<Seller> sellerList = new ArrayList<Seller>();

    @Override
    public boolean addSeller(Seller aSeller) {
        // Add the seller to the list of sellers
        sellerList.add(aSeller);
        return false;
    }

    @Override
    public void processRequests() {
        // Read the requests from the file "PortalToPlatform.txt"
        File myFile = new File("PortalToPlatform.txt");
        try (Scanner sc = new Scanner(myFile)) {
            // If the file is empty, print a message and return
            if (!sc.hasNextLine()) {
                System.out.println("Sorry we don't have any requests to process right now");
                return;
            }

            // Process each request in the file
            while (sc.hasNextLine()) {
                // Read a line from the file
                String line = sc.nextLine();
                System.out.println("Following request is being processed: ");
                System.out.println(line);

                // Split the line into tokens separated by one or more whitespace characters
                String[] tokens = line.split("\\s+");

                // If the request is a "Start" request...
                if (tokens[2].equals("Start")) {
                    // Generate a response containing the request ID and a list of categories for
                    // which products are available on the platform
                    String text = tokens[0];
                    text = text + " ";
                    text = text + tokens[1];
                    Boolean Mob = false;
                    Boolean Book = false;
                    Boolean GamingC = false;
                    // Check which categories have products available on the platform
                    for (Seller e : sellerList) {
                        if (!Mob) {
                            if (e.findProducts(Category.Mobile) != null) {
                                text += " ";
                                text += "Mobile";
                                Mob = true;
                            }
                        }
                        if (!Book) {
                            if (e.findProducts(Category.Book) != null) {
                                text += (" ");
                                text += ("Book");
                                Book = true;
                            }
                        }
                        if (!GamingC) {
                            if (e.findProducts(Category.GamingConsole) != null) {
                                text += (" ");
                                text += ("Gamingconsole");
                                GamingC = true;
                            }
                        }
                    }
                    // Write the response to the file "PlatformToPortal.txt"
                    try {
                        // System.out.println(text);
                        FileWriter fWriter = new FileWriter("PlatformToPortal.txt", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
                        // Append the response to the end of the file
                        bufferedWriter.append(text + "\n");

                        // Always close files.
                        bufferedWriter.close();
                    } catch (IOException e3) {
                        // Print the exception message
                        System.out.print(e3.getMessage());
                    }
                }

                // If the request is a "Buy" request...
                if (tokens[2].equals("Buy")) {
                    int found = 0;
                    // Search the list of sellers for a seller that has the specified product in
                    // stock and sufficient quantity
                    for (Seller e : sellerList) {
                        // Parse the quantity from the request
                        int quantity = Integer.parseInt(tokens[4]);
                        if (e.buyProduct(tokens[3], quantity)) {
                            found = 1;
                            // Generate a success response
                            try {
                                String text = tokens[0];
                                text += (" ");
                                text += (tokens[1]);
                                text += (" ");
                                text += ("Success");
                                // Write the response to the file "PlatformToPortal.txt"
                                FileWriter fWriter = new FileWriter(Globals.fromPlatform, true);
                                BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
                                // Append the response to the end of the file
                                bufferedWriter.append(text + "\n");
                                // Always close files.
                                bufferedWriter.close();
                            } catch (IOException e3) {
                                // Print the exception message
                                System.out.print(e3.getMessage());
                            }
                            break;
                        }
                    }
                    if (found == 0) {
                        // Generate a failure response
                        try {
                            String text = tokens[0];
                            text += (" ");
                            text += (tokens[1]);
                            text += (" ");
                            text += ("Fail");
                            // Write the response to the file "PlatformToPortal.txt"
                            FileWriter fWriter = new FileWriter(Globals.fromPlatform, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
                            // Append the response to the end of the file
                            bufferedWriter.append(text + "\n");
                            // Always close files.
                            bufferedWriter.close();
                        } catch (IOException e3) {
                            // Print the exception message
                            System.out.print(e3.getMessage());
                        }
                    }
                }

                // If the request is a "List" request...

                if (tokens[2].equals("List")) {
                    try (FileWriter fWriter = new FileWriter("PlatformToPortal.txt", true)) {
                        BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
                        // Iterate through each seller in the seller list
                        for (Seller e : sellerList) {
                            // Check the requested category
                            if (tokens[3].equals("Mobile")) {
                                // Find the seller's products in the Mobile category
                                ArrayList<Product> P = e.findProducts(Category.Mobile);
                                // If the seller has products in the Mobile category
                                if ((P.get(0) != null)) {
                                    // Iterate through each product
                                    for (int i = 0; i < P.size(); i++) {
                                        // Create the response string
                                        String text = tokens[0];
                                        text += (" ");
                                        text += (tokens[1]);
                                        text += (" ");
                                        text += (P.get(i).getName());
                                        text += (" ");
                                        text += (P.get(i).getProductID());
                                        text += (" ");
                                        String Price = String.valueOf(P.get(i).getPrice());
                                        String Qty = String.valueOf(P.get(i).getQuantity());
                                        text += (Price);
                                        text += (" ");
                                        text += (Qty);
                                        // Append the response to the file "PlatformToPortal.txt"
                                        bufferedWriter.append(text + "\n");
                                    }
                                }

                            }
                            if (tokens[3].equals("Book")) {
                                // Find the seller's products in the Book category
                                ArrayList<Product> P = e.findProducts(Category.Book);
                                if (P.get(0) != null) {
                                    for (int i = 0; i < P.size(); i++) {
                                        // Iterate through each product
                                        // Create the response string
                                        String text = tokens[0];
                                        text += (" ");
                                        text += (tokens[1]);
                                        text += (" ");
                                        text += (P.get(i).getName());
                                        text += (" ");
                                        text += (P.get(i).getProductID());
                                        text += (" ");
                                        String Price = String.valueOf(P.get(i).getPrice());
                                        String Qty = String.valueOf(P.get(i).getQuantity());
                                        text += (Price);
                                        text += (" ");
                                        text += (Qty);
                                        // Append the response to the file "PlatformToPortal.txt"
                                        bufferedWriter.append(text + "\n");
                                    }

                                }
                            }
                            if (tokens[3].equals("GamingConsole")) {
                                // Find the seller's products in the GamingConsole category
                                ArrayList<Product> P = e.findProducts(Category.GamingConsole);
                                if ((P.get(0) != null)) {
                                    for (int i = 0; i < P.size(); i++) {
                                        // Iterate through each product
                                        // Create the response string
                                        String text = tokens[0];
                                        text += (" ");
                                        text += (tokens[1]);
                                        text += (" ");
                                        text += (P.get(i).getName());
                                        text += (" ");
                                        text += (P.get(i).getProductID());
                                        text += (" ");
                                        String Price = String.valueOf(P.get(i).getPrice());
                                        String Qty = String.valueOf(P.get(i).getQuantity());
                                        text += (Price);
                                        text += (" ");
                                        text += (Qty);
                                        bufferedWriter.append(text + "\n");
                                    }
                                }

                            }

                        }
                        bufferedWriter.close();
                    } catch (IOException e3) {

                        // Print the exception
                        System.out.print(e3.getMessage());
                    }

                }

            }
            sc.close();
        } catch (NumberFormatException | FileNotFoundException e1) {
            // TODO Auto-generated catch block

            // System.out.print("exceptionnn");
            e1.printStackTrace();
        }

        PrintWriter pw;
        try {
            pw = new PrintWriter("PortalToPlatform.txt");
            pw.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // System.out.print("exceptionnn");
            e.printStackTrace();
        } // Deleting all the processed requests from the .txt file
    }
}