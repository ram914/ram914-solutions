import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashSet;

// RouteFinder will have public methods:
public class RouteFinder {

    ArrayList<Line> lines;
    HashSet<Line> visitedLines;
    ArrayList<String> output;
    boolean isTrainStartFromHere;

    // Constructor for RouteFinder
    public RouteFinder() {
        lines = new ArrayList<>();
        visitedLines = new HashSet<>();
        output = new ArrayList<>();
        isTrainStartFromHere = true;
    }

    /*
       Red 5
       A B C D E
       Blue 6
       F G C H I J
       Green 6
       F K L M N O
       Yellow 5
       P Q L R S
     */
    public void readLines(String fileName) throws Exception{

        Scanner in = new Scanner(new File(fileName));
        while(in.hasNext()) {

            String line = in.nextLine(); // read a line
            //System.out.println("Line read : "+line);
            String name = line.substring(0, line.indexOf(' ')); // read name
            int numberOfStations = Integer.parseInt(line.substring(line.indexOf(' ')+1)); // read num of stations
            //System.out.println("Line is ["+name+"]"+", num : "+numberOfStations);
            Line lineObj = new Line(name); // create a line object with the name

            // read next line, tokenize it
            StringTokenizer tok = new StringTokenizer(in.nextLine(), " ");
            while(tok.hasMoreTokens()) { // every token is a station name
                String token = tok.nextToken();

                if(token.length() > 0) { // add the token as a station to the line
                    //System.out.println("token : "+token);
                    lineObj.addStation(token);
                }
            }

            //System.out.println("Line : "+lineObj); // just for info purpose printing it
            lines.add(lineObj); // add this line to the lines list

        }
        in.close();
    }

    public boolean hasRoute(String startStation, String endStation) throws Exception{

        for(Line l: lines) { // iterate over all stations

            // if we have a line which was not crossed previously and it contains the
            // start station, then goto that line
            if((!isLineCrossed(l)) && l.hasStation(startStation)) {
                if(isTrainStartFromHere) {
                    // note the original start station
                    System.out.println("Line "+l.getName()+" has station "+startStation);
                    output.add("Line "+l.getName()+" has station "+startStation);
                    isTrainStartFromHere = false;
                }

                // now we know the start station
                // first find if you have destination also in this line
                if(l.hasStation(endStation)) {
                    output.add("continue to "+endStation+" continue in line "+l.getName());
                    //System.out.println("Station "+endStation+" is in line "+l.getName());
                    return true;
                }

                setLineCrossed(l); // set this line l as crossed, so that we dont come to this line next time

                // if the destination is not in the same line
                // then recurse over all the other lines
                boolean isRouteFound = false; // hold the recursive function call output
                for(Line cross_line : lines) { // loop over all lines

                    //System.out.println("l:"+l.getName()+", cross_line:"+cross_line.getName());
                    //System.out.println("cross_line:"+(isLineCrossed(cross_line))+", l.equals : "+(l.equals(cross_line)));
                    // if the line is not current line l and is not yes crossed then
                    if((!isLineCrossed(cross_line)) && (!l.equals(cross_line))) {
                        // find the crossing station between current line and this cross line
                        String cross_station = l.findTransfer(cross_line);
                        if(!cross_station.isEmpty()) { // if corss station does exist, then
                            //System.out.println("Switching from Line "+l.getName()+" to Line "+cross_line.getName()+" at "+cross_station);
                            // switch to that station in the cross line and try to find the end station in the cross line
                            isRouteFound = hasRoute(cross_station, endStation);
                            //Thread.sleep(1000);
                            // if the route round, return true
                            if(isRouteFound) {
                                output.add(1, "Switching from Line "+l.getName()+" to Line "+cross_line.getName()+" at "+cross_station);
                                return isRouteFound;
                            }
                        }
                    }
                }

                return isRouteFound;
            }
        }
        return false;
    }

    public void printRoute() {
        System.out.println("===== OUTPUT =====");
        for(String route: output)
            System.out.println(route);
    }

    private boolean isLineCrossed(Line line) {
        return visitedLines.contains(line);
    }

    private void setLineCrossed(Line line) {
        visitedLines.add(line);
    }
}
