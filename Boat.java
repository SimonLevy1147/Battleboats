import java.util.Arrays;

public class Boat {
    private int size;
    private boolean orientation;
    private Cell[] location;

    public Boat(int size, boolean orientation, Cell[] location) {
        this.size = size;
        this.orientation = orientation;
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOrientation() { //needed?
        return orientation;
    }

    public void setOrientation(boolean orientation) { //needed?
        this.orientation = orientation;
    }

    public Cell[] getLocation() {
        return location;
    }

    public void setLocation(Cell[] location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "size=" + size +
                ", vertical=" + orientation +
                ", location=" + Arrays.toString(location) +
                '}';
    }
}
