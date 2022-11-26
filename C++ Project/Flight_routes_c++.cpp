#include <iostream>
#include <fstream>
#include <string.h>
#include <vector>
#include <sstream>
#include <cstdlib>
#include <memory>
using namespace std;

// Declaring of member functions
vector<vector<string>> get_routes();
vector<vector<string>> get_airports();
vector<vector<string>> get_airlines();
vector<string> init_dest();

int main(void)
{

    vector<vector<string>> route_data = get_routes();
    vector<vector<string>> airport_data = get_airports();
    vector<string> start_end = init_dest();

    // Finding the route
    int num = 0;
    vector<int> num2;
    int num3 = 0;
    string start_point;
    string goal;

    for (int i = 0;;)
    {
        if (airport_data[i][2] == start_end[0])
        {
            num = i;
            break;
        }
        else
        {
            i++;
        }
    }

    for (int i = 0;;)
    {
        if (airport_data[i][2] == start_end[2])
        {
            num3 = i;
            break;
        }
        else
        {
            i++;
        }
    }

    start_point = airport_data[num][0];
    goal = airport_data[num3][0];

    for (int i = 0;;)
    {

        if (route_data[i][3] == start_point)
        {
            num2.push_back(i);
            start_point = route_data[i][5];
            i = 0;
        }
        if (start_point == goal)
        {
            break;
        }
        else
        {
            i++;
        }
    }

    // Writing routes to .txt file
    int stops = 0;
    int flights = 0;
    ofstream path_out("path_out.txt");
  
    for (int i = 0; i < num2.size(); i++)
    {
        path_out << route_data[num2[i]][0] + " from " + route_data[num2[i]][2] + " to " 
        + route_data[num2[i]][4] + " " + route_data[num2[i]][7] + " stops\n";
        flights = i + 1;
        stops += stoi(route_data[num2[i]][7]);

    }
        path_out << "Total flights: " << flights << endl;
        path_out << "Total additional stops: " << stops << endl;
        system("pause");
        return 0;
    }



    // Member function to read and store routes data in a 2D vector
    vector<vector<string>> get_routes()
    {
        vector<vector<string>> route;
        ifstream file("routes.csv");
        while (file)
        {
            string line;
            if (!getline(file, line))
                break;

            istringstream ss(line);
            vector<string> records;

            while (ss)
            {
                string s;
                if (!getline(ss, s, ','))
                    break;
                records.push_back(s);
            }
            route.push_back(records);
        }

        file.close();
        return route;
    }

    // Member function to read and store airport data in a 2D vector
    vector<vector<string>> get_airports()
    {
        vector<vector<string>> airport;
        ifstream file("airports.csv");
        while (file)
        {
            string line;
            if (!getline(file, line))
                break;

            istringstream ss(line);
            vector<string> records;

            while (ss)
            {
                string s;
                if (!getline(ss, s, ','))
                    break;
                records.push_back(s);
            }
            airport.push_back(records);
        }

        file.close();
        return airport;
    }

    // Member function to read and store airline data in a 2D vector
    vector<vector<string>> get_airlines()
    {
        vector<vector<string>> airline;
        ifstream file("airlines.csv");
        while (file)
        {
            string line;
            if (!getline(file, line))
                break;

            istringstream ss(line);
            vector<string> records;

            while (ss)
            {
                string s;
                if (!getline(ss, s, ','))
                    break;
                records.push_back(s);
            }
            airline.push_back(records);
        }

        file.close();
        return airline;
    }

    vector<string> init_dest()
    {
        vector<string> init_dest;
        ifstream file("path.txt");
        string str;
        file >> str;
        stringstream ss(str);

        while (ss.good())
        {
            string substr;
            if (!getline(ss, substr, ','))
                break;
            init_dest.push_back(substr);
        }

        file.close();
        return init_dest;
    }