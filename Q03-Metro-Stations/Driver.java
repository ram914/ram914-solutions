import java.util.Scanner;
import java.util.StringTokenizer;

// Driver Class
public class Driver {
    public static void main(String... args) throws Exception {

        Scanner in = new Scanner(System.in); // to read user input

        String fileName = "testData.txt"; // input data file name
        RouteFinder rf = new RouteFinder(); // create RouteFinder
        rf.readLines(fileName); // read the input file

        System.out.print("Enter two stations separated by commas: ");
        StringTokenizer tok = new StringTokenizer(in.nextLine(), ", ");

        // find the route between two stations
        if(rf.hasRoute(tok.nextToken(), tok.nextToken())) {
            rf.printRoute();
        } else {
            System.out.println("No Route Exists between given stations.");
        }
    }
}
