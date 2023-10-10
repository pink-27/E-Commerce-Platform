
#include <bits/stdc++.h>
#include "../ecomm/Portal.h"
#include <fstream>
#include <limits>
using namespace std;
#ifndef PORTAL_1_H
#define PORTAL_1_H
class Portal_1 : public Portal
{
private:
    map<int, string> requestMapping; //? maps the request id of our user to the command that our respective user requested for
    map<int, int> sortMapping;       //? maps the request id of our user to the sorting parameter that the user requested for
    int request_id = 0;
    bool is_empty(std::fstream &pFile); //? function to check if the file is empty

    bool cmpName(string a, string b); //? our comparator that'll help us sort the product ids lexographically in increasing order
    bool cmpPrice(float a, float b);  //? our comparator that'll help us sort the product prices in increasing order
    int portal_id = 1;                //? our portal identifier

public:
    void processUserCommand(string command); //! our portal process the commands from the user here
    void checkResponse();
};

#endif
