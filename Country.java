import java.util.ArrayList;

public class Country {
    private final String name;
    private final String language;
    private final Boolean openToTourists;
    private final ArrayList<Destination> destinationArrayList = new ArrayList<>();

    //Constructor
    public Country(String name, String language, Boolean openToTourists){
        this.name = name;
        this.language = language;
        this.openToTourists = openToTourists;
    }

    public String getName(){
        return name; }
    public String getLanguage(){
        return language; }
    public Boolean getOpenToTourists(){
        return openToTourists; }
    public int getNumDestinations(){
        return destinationArrayList.size();
    }

    public String toString(){
        return name + " " + language + " " + openToTourists;
    }

    public void addDestination(Destination destination){
        destinationArrayList.add(destination);
    }

    //Returns the highest destination capacity in a country
    public Destination destinationHighestCapacity(){
        Destination highestCapacityDestination = null;
        int highestCapacity = 0;
        for (Destination destination: destinationArrayList){
            if (destination.getCapacity() > highestCapacity){
                highestCapacityDestination = destination;
                highestCapacity = destination.getCapacity();
            }
        }
        return highestCapacityDestination;
    }

    //Returns the average destination capacity in a country
    public float averageDestinationCapacity(){
        float sumCapacity = 0;
        for (Destination destination: destinationArrayList){
            sumCapacity += destination.getCapacity();
        }
        float numDestinations = getNumDestinations();
        return sumCapacity / numDestinations;
    }

     //method to search through country's destination array list for destination by name and return all info
     public Destination returnDestinationByName(String desiredName){
        for (Destination destination: destinationArrayList){
            if (desiredName.equals(destination.getName())){
                return destination;
            }
        } return null;
     }

//

    public ArrayList<Destination> returnCountryDestinationsByCapacity(int desiredCapacity){
        ArrayList<Destination> countryBigDestinations = new ArrayList<>();
        for (Destination destination: destinationArrayList){
            if (destination.getCapacity() > desiredCapacity){
                countryBigDestinations.add(destination);
            }
        }
        return countryBigDestinations;
    }


}
