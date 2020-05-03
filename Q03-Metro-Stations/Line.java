import java.util.HashSet;

// Line class
public class Line {

    private HashSet<String> stations; // to store all stations in line
    private String name; // name of the line

    // constructor of line
    public Line(String name) {
        this.name = name;
        stations = new HashSet<>(); // hash set has O(1) lookup
    }

    public void addStation(String station) {
        stations.add(station);
    }

    public boolean hasStation(String station) {
        return stations.contains(station);
    }

    public String findTransfer(Line otherLine) {
        for(String station : otherLine.stations) { // loop over stations
            if(hasStation(station)) { // if any station is in common then return it
                return station;
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    @Override
    public String toString() {
        return name + ": "+stations;
    }

    // override equals and hash code functions to
    // compare two lines
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Line)) {
            return false;
        }
        Line line = (Line) o;
        return line.name.equals(name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + stations.hashCode();
        return result;
    }
}
