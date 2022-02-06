public class Destination {

    //Variables final, apart from capacity - could feasibly increase or decrease
    private final String name;

    //Latitude and longitude positive for N and E, negative for S and W
    private final double latitude;
    private final double longitude;
    private final int capacity;

    //Constructor
    public Destination(String name, double latitude, double longitude, int capacity){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
    }

    public String getName(){
        return name;
    }
    public double getLatitude(){
        return latitude; }
    public double getLongitude(){
        return longitude; }
    public int getCapacity(){
        return capacity; }

    public String toString() {
        return name + " " + latitude + " " + longitude + " " + capacity; }
}
