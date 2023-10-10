#include "Portal_1.h"

bool Portal_1::is_empty(std::fstream &pFile) //? function to check if the file is empty
{
    return pFile.peek() == std::fstream::traits_type::eof();
}

bool Portal_1::cmpName(string a, string b) //? our comparator that'll help us sort the product ids lexographically in increasing order
{
    return a > b;
}

bool Portal_1::cmpPrice(float a, float b)
{ //? our comparator that'll help us sort the product prices in increasing order
    return a > b;
}

void Portal_1::processUserCommand(string command)
{
    if (command == "Buy") //? if the command is buy
    {
        string sellerID; //? this will store the seller ID that the user wants to buy the product from
        cout << "Enter seller ID (seller1/seller2/seller3): " << endl;
        cin >> sellerID;
        string itemName; //? this stores the name of the product
        cout << "Enter item name ({sellerID}-X, X is the product name): " << endl;
        cin >> itemName;
        int quantity; //? this stores the quantity
        cout << "Enter quantity: " << endl;
        cin >> quantity;

        fstream myFile; //? we create a myFile object

        myFile.open("PortalToPlatform.txt", ios::app); //? we open our PortalToPlatform.txt file in append mode

        if (myFile.is_open()) //? if the files opens successfully
        {
            //? we write on our file in the appropriate format
            myFile << portal_id << " "
                   << request_id
                   << " " << command << " "
                   << sellerID << "-" << itemName << " " << quantity << endl;
        }
        myFile.close(); //? we close the file
        cout << endl;
        cout << request_id << " is your request id!, hold onto it" << endl;
        cout << endl;
        requestMapping[request_id] = command; //? we map the request_id to the command, we'll later need this while processing the PlatformToPortal file
        request_id++;                         //? we increment the request_id
    }
    else if (command == "Start") //? if the command is start
    {
        fstream myFile;

        myFile.open("PortalToPlatform.txt", ios::app); //? we open the file in append mode
        if (myFile.is_open())
        {
            //? we write on our file in the appropriate format
            myFile << portal_id << " " << request_id
                   << " Start" << endl;
        }
        myFile.close();
        cout << endl;
        cout << request_id << " is your request id!, hold onto it" << endl;
        cout << endl;
        requestMapping[request_id] = command; //? we map the request_id to the command, we'll later need this while processing the PlatformToPortal file
        request_id++;
    }
    else //? if the command is list
    {
        cout << "Please enter the category you want to list:" << endl;
        string categoryName; //? name of the category the the user wants to see
        cin >> categoryName;
        cout << "Please enter the how you want us to list the products, the available options are:-\n 1. increasing order of price\n 2. alphabatically\n Kindly select one option buy entring the option number: ";
        int SortingParameter; //? the sorting parameter, aplhabatic or numeric
        cin >> SortingParameter;

        fstream myFile;

        myFile.open("PortalToPlatform.txt", ios::app); //? we open the file in append mode
        if (myFile.is_open())
        {
            myFile << portal_id << " " << request_id
                   << " List " << categoryName << endl;
        }
        myFile.close();
        cout << endl;
        cout << request_id << " is your request id!, hold onto it" << endl;
        requestMapping[request_id] = command;       //? we map the request_id to the command, we'll later need this while processing the PlatformToPortal file
        sortMapping[request_id] = SortingParameter; //? we map the request_id to the our sorting parameter, we'll later need this while processing the Platform
        request_id++;
    }
}

void Portal_1::checkResponse()
{
    cout << endl;
    fstream myFile;

    myFile.open("PlatformToPortal.txt", ios::in); //? we open the file in read mode

    if (is_empty(myFile)) //? if the file is empty i.e. the sllers haven't processed the command yet
    {
        cout << "Sorry we are processing your request, kindly check back later" << endl;
        cout << endl;
        return;
    }
    vector<string> lines; //? we sotre the lines of PlatformToPortal in this vector, each line repersents an element in this vector
    string data;
    while (getline(myFile, data)) //? we add the string to the vector
    {
        lines.push_back(data);
    }
    if (myFile.is_open())
    {
        string line; //? this variable will store the line which we are currently reading
        for (int k = 0; k < lines.size(); k++)
        {
            line = lines[k];
            vector<string> tokens; //? we are the words of the line this vector
            string token = "";
            for (int i = 0; i < line.size(); i++)
            {
                if (line[i] == ' ')
                {
                    tokens.push_back(token);
                    token = "";
                }
                else if (i == line.size() - 1)
                {
                    token += line[i];
                    tokens.push_back(token);
                    token = "";
                }
                else
                {
                    token += line[i];
                }
            }

            int requested_id = stoi(tokens[1]); //? the second element of tokens is the requested id

            if (requestMapping[requested_id] == "Start") //? using the map we check the command type, and if it's start
            {

                cout << "Request ID - " << requested_id << " :- These are all the availabe categories in our supermart:" << endl;
                for (int i = 2; i < tokens.size(); i++) //? we print all the categories line by line
                {
                    cout << tokens[i] << endl;
                }
                cout << endl;
            }
            else if (requestMapping[requested_id] == "Buy") //? if the request was to buy something
            {
                cout << "Request ID - " << requested_id << " :- Status of your purchase:" << endl; //? we ouput the status of the request (sucess/failure)
                cout << tokens[2] << endl;
                cout << endl;
            }
            else if (requestMapping[requested_id] == "List") //? if the request was to list the products of a category
            {

                string product;
                vector<vector<string> > listings; //? we store the different lines/products in this vector

                int l = k;
                int flag = 0; //? this will tell weather we have more requests after our current command or not
                for (l = k; l < lines.size(); l++)
                {
                    product = lines[l]; //? we store each item's properties in this string

                    string list = "";
                    vector<string> listing;
                    for (int i = 0; i < product.size(); i++)
                    {
                        if (product[i] == ' ')
                        {

                            listing.push_back(list);
                            list = "";
                        }
                        else if (i == product.size() - 1)
                        {

                            list += product[i];

                            listing.push_back(list);
                            list = "";
                        }
                        else
                        {

                            list += product[i];
                        }
                    }
                    if (stoi(listing[1]) == requested_id) //? if our current line matches the requested_id then we add the properties in our vector
                        listings.push_back(listing);
                    else
                    { //? we break the loop if our current line does not match the requested_id
                        flag = 1;
                        break;
                    }
                }

                //? we adujust our global line pointer
                if (flag == 1)
                {
                    k = l - 1;
                }
                else
                {
                    k = l;
                }

                //? if the list request was to sort numerically(prices)
                if (sortMapping[requested_id] == 1)
                {
                    //? we do a bubble sort using our comparator
                    for (int i = 0; i < listings.size() - 1; i++)
                    {
                        for (int j = 0; j < listings.size() - i - 1; j++)
                        {

                            if (cmpPrice(stof(listings[j][4]), stof(listings[j + 1][4])))
                            {
                                swap(listings[j], listings[j + 1]);
                            }
                        }
                    }
                    requested_id = stoi(tokens[1]);

                    //? we output our sorted listing
                    cout << "Request id- " << requested_id << "-: These are the products with increasing prices:\n";
                    for (int i = 0; i < listings.size(); i++)
                    {
                        for (int j = 0; j < listings[i].size(); j++)
                        {
                            cout << listings[i][j] << " ";
                        }
                        cout << endl;
                    }
                }
                else //? if the list request was to sort alphabaticaly
                {
                    //? we do a bubble sort using our comparator
                    for (int i = 0; i < listings.size() - 1; i++)
                    {
                        for (int j = 0; j < listings.size() - i - 1; j++)
                        {
                            int price1 = 0;
                            int price2 = 0;

                            if (cmpName(listings[j][2], listings[j + 1][2]))
                            {
                                swap(listings[j], listings[j + 1]);
                            }
                        }
                    }
                    cout << "Request id- " << requested_id << "-: These are the products sorted lexographically:\n";
                    //? we output our sorted listing
                    for (int i = 0; i < listings.size(); i++)
                    {
                        for (int j = 2; j < listings[i].size(); j++)
                        {
                            cout << listings[i][j] << " ";
                        }
                        cout << endl;
                    }
                }
                cout << endl;
            }
        }
        //? we empty our files
        myFile.open("PortalToPlatform.txt", ios::out);
        myFile.close();
        myFile.open("PlatformToPortal.txt", ios::out);
        myFile.close();
    }
}
