import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Flight_routes_v2 {
    // Reading and storing routes
    static String[][] get_routes() throws IOException, CsvException {
        CSVReader ro = new CSVReader(
                new FileReader(new File("C:\\Users\\afeny\\OneDrive\\Desktop\\ICP\\Individual Project 1\\routes.csv")));
        List<String[]> route_list = ro.readAll();
        String[][] route_data = new String[route_list.size()][];
        route_data = route_list.toArray(route_data);
        return route_data;
    }

    // Reading and storing airports
    static String[][] get_airports() throws IOException, CsvException {
        CSVReader ap = new CSVReader(new FileReader(
                new File("C:\\Users\\afeny\\OneDrive\\Desktop\\ICP\\Individual Project 1\\airports.csv")));
        List<String[]> airport_list = ap.readAll();
        String[][] airport_data = new String[airport_list.size()][];
        airport_data = airport_list.toArray(airport_data);
        return airport_data;

    }

    // Reading and storing airlines
    static String[][] get_airlines() throws IOException, CsvException {
        CSVReader al = new CSVReader(new FileReader(
                new File("C:\\Users\\afeny\\OneDrive\\Desktop\\ICP\\Individual Project 1\\airlines.csv")));
        List<String[]> airline_list = al.readAll();
        String[][] airline_data = new String[airline_list.size()][];
        airline_data = airline_list.toArray(airline_data);
        return airline_data;
    }

    public static void main(String[] args) throws IOException, CsvException {

        String[][] route_data = get_routes();
        String[][] airport_data = get_airports();
        get_airlines();

        // Taking user input of start and destination location from 
        List<String> startend = new ArrayList<String>();
        Scanner s_e = new Scanner(
                new FileReader("C:\\Users\\afeny\\OneDrive\\Desktop\\ICP\\Individual Project 1\\path.txt"))
                .useDelimiter(",");
        String read;
        while (s_e.hasNextLine()) {
            read = s_e.next();
            startend.add(read);
        }
        String[] start_end = startend.toArray(new String[0]);

        // Finding the route
        int num = 0;
        List<Integer> num2 = new ArrayList<Integer>();
        int num3 = 0;

        for (int i = 0;;) {
            if (airport_data[i][2].equals(start_end[0])) {
                num = i;
                break;
            } else {
                i++;
            }
        }

        for (int i = 0;;) {
            if (airport_data[i][2].equals(start_end[2])) {
                num3 = i;
                break;
            } else {
                i++;
            }
        }

        String start_point = airport_data[num][0];
        String goal = airport_data[num3][0];

        for (int i = 0;;) {
            if (route_data[i][3].equals(start_point)) {
                num2.add(i);
                start_point = route_data[i][5];
                i = 0;
            }
            if (start_point.equals(goal)) {
                break;
            } else {
                i++;
            }

        }

        // Writing routes into .txt file
        int stops = 0;
        int flights = 0;
        File path_file = new File("path.txt");
        FileOutputStream path_out = new FileOutputStream(path_file);
        BufferedWriter pob = new BufferedWriter(new OutputStreamWriter(path_out));

        if (path_file.length() != 0) {
            new FileOutputStream(path_file).close();
        } else {
            for (int i = 0; i < num2.size(); i++) {
                pob.write(route_data[num2.get(i)][0] + " from " + route_data[num2.get(i)][2] + " to "
                        + route_data[num2.get(i)][4] + " " + route_data[num2.get(i)][7] + " stops");
                flights = i;
                stops += Integer.parseInt(route_data[num2.get(i)][7]);
                pob.newLine();
            }
        }
        pob.write("Total flights: " + flights + 1);
        pob.newLine();
        pob.write("Total additional flights: " + stops);
        pob.close();
    }

}
