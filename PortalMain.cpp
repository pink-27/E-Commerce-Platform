#include "PortalMain.h"
#include "Derived/Portal_1.h"
#include <bits/stdc++.h>
#include <fstream>

using namespace std;

//! use {clang++ *.cpp Derived/*.h ecomm/*.h Derived/*.cpp} to complie
//! use ./a.out to run the portal

int main()
{
    // Instantiate a new portal
    Portal *portal = new Portal_1();

    // Open both the files for writing, overwriting any previous content
    fstream myFile;
    myFile.open("PortalToPlatform.txt", ios::out);
    myFile.open("PlatformToPortal.txt", ios::out);

    // Print instructions for using the portal
    cout << "1. To Get requestID and start a new purchase, enter Start." << endl;
    cout << "2. To get a Listing of the product, enter List." << endl;
    cout << "3. To Check any response from platform, enter Check." << endl;
    cout << "4. To exit, enter exit." << endl;

    // Continue processing user commands until the user enters "exit"
    while (1)
    {
        string s;
        cin >> s;
        if (s == "exit")
        {
            break;
        }

        // Call the appropriate function in the portal based on the user's command
        if (s == "Buy")
        {
            portal->processUserCommand("Buy");
        }
        else if (s == "Start")
        {
            portal->processUserCommand("Start");
        }
        else if (s == "List")
        {
            portal->processUserCommand("List");
        }
        else if (s == "Check")
        {
            portal->checkResponse();
        }
        else
        {
            cout << "Invalid Input, try again" << endl;
        }
    }
}
