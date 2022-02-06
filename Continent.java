import java.util.ArrayList;

public class Continent {
    private final String name;
    private final ArrayList<Country> countryArrayList = new ArrayList<>();

    //Constructor
    public Continent(String name){
        this.name = name;
    }

    public String getName() {
        return name; }

    public String toString(){
        return name;
    }

    public void addCountry(Country country){
        countryArrayList.add(country);
    }

    //Returns a country from the continent that matches a String
    public Country returnCountryByName(String desiredName){
        for (Country country: countryArrayList){
            if (desiredName.equals(country.getName())){
                return country;
            }
        } return null;
    }

    //Returns an array list of all countries in a continent
    public ArrayList<Country> getAllCountries(){
        return countryArrayList;
    }

    //Method returning the Country which has the highest average Destination capacity
    public Country countryHighAvDestCap(){
        Country targetCountry = null;
        float highAvCap = 0;
        for (Country country : countryArrayList){
            if (country.averageDestinationCapacity() > highAvCap){
                targetCountry = country;
                highAvCap = country.averageDestinationCapacity();
            }
        } return targetCountry;
    }

    //The Destination with the highest capacity (in the continent)
    public Destination continentDestHighCap(){
        Destination targetDest = null;
        int highCap = 0;
        for (Country country: countryArrayList){
            if (country.destinationHighestCapacity().getCapacity() > highCap) {
                highCap = country.destinationHighestCapacity().getCapacity();
                targetDest = country.destinationHighestCapacity();
            }
        }  return targetDest;
    }

    //Returns a list of all Countries that have been recorded, that have a capacity larger than a given number.
    public ArrayList<Destination> returnContinentDestinationsByCapacity(int capacity){
        ArrayList<Destination> bigDestinations = new ArrayList<>();
        for (Country country: countryArrayList){
            bigDestinations.addAll(country.returnCountryDestinationsByCapacity(capacity));
        }
        return bigDestinations;
    }
}

































