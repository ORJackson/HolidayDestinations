import java.util.ArrayList;
import java.util.Scanner;

public class Booking {

    private static final ArrayList<Continent> CONTINENTS = new ArrayList<>();

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final Continent AUSTRALASIA = new Continent("australasia");
    private static final Continent SOUTH_AMERICA = new Continent("south america");
    private static final Continent NORTH_AMERICA = new Continent("north america");
    private static final Continent AFRICA = new Continent("africa");
    private static final Continent EUROPE = new Continent("europe");
    private static final Continent ANTARCTICA = new Continent("antarctica");
    private static final Continent ASIA = new Continent("asia");

    public static void main(String[] args) {

        CONTINENTS.add(AUSTRALASIA);
        CONTINENTS.add(SOUTH_AMERICA);
        CONTINENTS.add(NORTH_AMERICA);
        CONTINENTS.add(AFRICA);
        CONTINENTS.add(EUROPE);
        CONTINENTS.add(ANTARCTICA);
        CONTINENTS.add(ASIA);

        //Some data to start with:

       Country indonesia = new Country("indonesia", "indonesian", true);
       ASIA.addCountry(indonesia);
       Country philippines = new Country("philippines", "filipino", true);
       ASIA.addCountry(philippines);
       Country china = new Country("china", "mandarin", false);
       ASIA.addCountry(china);

       Destination canggu = new Destination("canggu", -8.6478, 115.1385, 40000);
       indonesia.addDestination(canggu);
       Destination pacitan = new Destination("pacitan", -8.1798, 111.1057, 600000);
       indonesia.addDestination(pacitan);
       Destination yogyakarta = new Destination("yogyakarta", -7.7956, 110.3695, 422000);
       indonesia.addDestination(yogyakarta);
       Destination jakarta = new Destination("jakarta", -6.2088, 106.8456, 11000000);
       indonesia.addDestination(jakarta);

       Destination manila = new Destination("manila", 14.5995, 120.9842, 1780000);
       philippines.addDestination(manila);
       Destination siargao = new Destination("siargao", 9.8482, 126.0458, 200000);
       philippines.addDestination(siargao);

       Destination beijing = new Destination("beijing", 39.9042, 116.4074, 21540000);
       china.addDestination(beijing);
       Destination shanghai = new Destination("shanghai", 31.2304, 121.4737, 26320000);
       china.addDestination(shanghai);
       Destination chengdu = new Destination("chengdu", 30.5723, 104.0665, 16330000);
       china.addDestination(chengdu);

        //A while loop that won't end until user types exit at main menu
        //This is the main menu
        Scanner in = new Scanner(System.in);
        String mainMenuInput = " ";
        while (!mainMenuInput.equals("exit")) {
            printMainMenu();
            mainMenuInput = safeNext(in);
            switch (mainMenuInput) {
                case "1":
                    userAddCountry(in);
                    break;
                case "2":
                    userAddDestination(in);
                    break;
                case "3":
                    countryWithHighestAvCapDest();
                    break;
                case "4":
                    highestCapacityDestination();
                    break;
                case "5":
                    printAllDestCapLargerThanX(in);
                    break;
                case "exit":
                    System.out.println("See you soon!");
                    break;
                default:
                    printInvalidInputError();
                    break;
            }
        }
        in.close();
    }

    //Method to print main menu
    private static void printMainMenu() {
        System.out.println();
        System.out.println("Hello user. Welcome to the main menu. Please select an option:");
        System.out.println("Add a country (type: 1)");
        System.out.println("Add a destination (type: 2)");
        System.out.println("Find the country with the highest average capacity destination (type: 3)");
        System.out.println("Find the highest capacity destination in the system (type: 4)");
        System.out.println("Find all destinations with a capacity greater than a certain number (type: 5)");
        System.out.println("Exit (Type: exit)");
    }

    //OPTION 1
    //Method for option 1 - user add a country
    private static void userAddCountry(Scanner in) {
        System.out.println();
        System.out.println("To add a country please input data as requested.");
        System.out.println("The continents to choose from are:");
        printContinentNames();
        Continent inputContinent = whatContinent(in);
        String inputCountry = whatCountry(in, inputContinent);
        String inputLanguage = whatLanguage(in);
        boolean inputOpenToTourists = isOpenToTourists(in);
        Country newCountry = new Country(inputCountry, inputLanguage, inputOpenToTourists);
        inputContinent.addCountry(newCountry);
        System.out.println("Continent: " + capitaliseFirstLetters(inputContinent.getName()));
        System.out.println("Country: " + capitaliseFirstLetters(newCountry.getName()));
        System.out.println("Language: " + capitaliseFirstLetters(newCountry.getLanguage()));
        if (newCountry.getOpenToTourists()) {
            System.out.println(capitaliseFirstLetters(inputCountry) + " is open to tourists");
        } else {
            System.out.println(capitaliseFirstLetters(inputCountry) + " is not open to tourists");
        }
        System.out.println("That country has been added.");
    }

    //Method for option 1, asks user what continent they want to add a country to
    private static Continent whatContinent(Scanner in) {
        System.out.println("What is the name of the continent?");
        String userInput = safeNext(in);
        boolean validInput = false;
        while (!validInput) {
            for (Continent continent : CONTINENTS) {
                if (continent.getName().toLowerCase().equals(userInput)) {
                    validInput = true;
                    break;
                }
            }
            if (!(validInput)) {
                printInvalidInputError();
                userInput = safeNext(in);
            }
        }
        return returnContinentByName(userInput);
    }


    //Method for option 1, asks user what country they want to add
    private static String whatCountry(Scanner in, Continent continentName) {
        String userInput = " ";
        System.out.println("What is the name of the country?");
        boolean validInput = false;
        while (!validInput) {
            userInput = safeNext(in);
            while (!isOnlyLetters(userInput)) {
                printInvalidInputError();
                userInput = safeNext(in);
            }
            //if the statement returns null country doesn't exist yet in this continent
            if (continentName.returnCountryByName(userInput) == null) {
                validInput = true;
            } else {
                System.out.println("This country has already been added, try a different one.");
            }
        }
        return userInput;
    }

    //Method for option 1, asks what language is spoken in the country
    private static String whatLanguage(Scanner in) {
        System.out.println("What language is spoken in this country?");
        String userInput = safeNext(in);
        while (!isOnlyLetters(userInput)) {
            printInvalidInputError();
            userInput = safeNext(in);
        }
        return userInput;
    }

    //Method for option 1, asks if the country is open to tourists
    private static boolean isOpenToTourists(Scanner in) {
        System.out.println("Is the country open to tourists? Type yes or no.");
        boolean isOpen = false;
        String userInput;
        boolean validAnswer = false;
        while (!validAnswer) {
            userInput = safeNext(in);
            if (userInput.equals("yes")) {
                isOpen = true;
                validAnswer = true;
            } else if (userInput.equals("no")) {
                validAnswer = true;
            } else {
                printInvalidInputError();
            }
        }
        return isOpen;
    }

    //OPTION 2
    //Method for option 2 - user add a destination
    private static void userAddDestination(Scanner in) {
        System.out.println();
        if (thereAreCountries()){
            System.out.println("To add a destination please input data as requested.");
            System.out.println("A destination is a city or town (no numbers!) "
                    + "it must exist within a country that exists on this database.");
            Country inputCountry = whatDestinationCountry(in);
            String inputDestinationName = whatDestinationName(in, inputCountry);
            int inputDestinationCapacity = whatDestinationCapacity(in);
            double inputDestinationLatitude = whatDestinationLatitudeOrLongitude(in, LATITUDE);
            double inputDestinationLongitude = whatDestinationLatitudeOrLongitude(in, LONGITUDE);
            Destination newDestination = new Destination(inputDestinationName, inputDestinationLatitude,
                    inputDestinationLongitude, inputDestinationCapacity);
            inputCountry.addDestination(newDestination);
            System.out.println("Destination country: " + capitaliseFirstLetters(inputCountry.getName()));
            System.out.println("Destination name: " + capitaliseFirstLetters(inputDestinationName));
            System.out.println("Destination capacity: " + inputDestinationCapacity);
            System.out.println("Destination latitude: " + inputDestinationLatitude);
            System.out.println("Destination longitude: " + inputDestinationLongitude);
            System.out.println("That destination has been added.");
        } else {
            System.out.println("There aren't any countries yet. Add countries from the main menu.");
        }
    }

    //Method for option 2, asks user what destination the country is in
    private static Country whatDestinationCountry(Scanner in) {
        System.out.println("In what country is the destination?");
        String userInput;
        Country inputCountry = null;
        boolean validInput = false;
        while (!validInput) {
            userInput = safeNext(in);
            if (!(getCountryFromAllContinents(userInput) == null)) {
                inputCountry = getCountryFromAllContinents(userInput);
                validInput = true;
            } else if (!isOnlyLetters(userInput)) {
                printInvalidInputError();
            } else {
                System.out.println("Country entered does not exist yet. Try a different country. Countries include:");
                printAllCountries();
            }
        }
        return inputCountry;
    }

    //Method for option 2, asks user for the destination name
    private static String whatDestinationName(Scanner in, Country country) {
        System.out.println("What is the name of the destination?");
        String userInput = "";
        boolean validInput = false;
        while (!validInput) {
            userInput = safeNext(in);
            if (!isOnlyLetters(userInput)) {
                printInvalidInputError();
            } else if (country.returnDestinationByName(userInput) == null) {
                validInput = true;
            } else if (country.returnDestinationByName(userInput) != null) {
                System.out.println("This destination is already saved. Try another destination for this country.");
            } else {
                System.out.println("Not sure how we got here");
            }
        }
        return userInput;
    }

    //Method for option 2, asks user what the destination capacity is
    private static int whatDestinationCapacity(Scanner in) {
        System.out.println("What is the capacity of the destination?");
        String userInput = safeNext(in);
        while (!isGreaterOrEqualZeroInt(userInput)) {
            printInvalidInputError();
            userInput = safeNext(in);
        }
        return Integer.parseInt(userInput);
    }

    //Method for option 2, asks user for destination latitude/ longitude
    private static double whatDestinationLatitudeOrLongitude(Scanner in, String latOrLong) {
        System.out.println("What is the destination's " + latOrLong);
        String userInput = safeNext(in);
        while (!(isLatOrLongDouble(userInput, latOrLong))) {
            printInvalidInputError();
            userInput = safeNext(in);
        }
        return Double.parseDouble(userInput);
    }


    //OPTION 3 STATISTICS
    //Method for option 3, gets the country with the highest average destination capacity in the world
    private static void countryWithHighestAvCapDest() {
        System.out.println();
        Country topCountry = null;
        float topCountryAverageCap = 0;
        for (Continent continent : CONTINENTS) {
            if (continent.countryHighAvDestCap() != null) {
                if (continent.countryHighAvDestCap().averageDestinationCapacity() > topCountryAverageCap) {
                    topCountry = continent.countryHighAvDestCap();
                    topCountryAverageCap = topCountry.averageDestinationCapacity();
                }
            }
        }
        if (topCountry != null) {
            System.out.println("The country with the highest average capacity is: " + capitaliseFirstLetters(topCountry.getName()));
            System.out.println("It's average capacity is: " + Math.round(topCountryAverageCap));

        } else {
            printNoResults();
        }
    }


    //Option 4 STATISTICS
    //Method for option 4, gets the highest capacity destination in the world
    private static void highestCapacityDestination() {
        System.out.println();
        Destination bigDestination = null;
        int highestCapacity = 0;
        for (Continent continent : CONTINENTS) {
            if (continent.continentDestHighCap() != null) {
                if (continent.continentDestHighCap().getCapacity() > highestCapacity) {
                    highestCapacity = continent.continentDestHighCap().getCapacity();
                    bigDestination = continent.continentDestHighCap();
                }
            }
        }
        if (highestCapacity > 0) {
            System.out.println("The highest capacity destination in the system is:");
            System.out.println(capitaliseFirstLetters(bigDestination.getName()));
            System.out.println("Capacity: " + highestCapacity);
            System.out.println("Latitude: " + bigDestination.getLatitude());
            System.out.println("Longitude: " + bigDestination.getLongitude());
        } else {
            printNoResults();
        }
    }

    //Option 5 STATISTICS
    //Method for option 5, gets all destinations with a capacity larger than a number inputted by user
    private static void printAllDestCapLargerThanX(Scanner in) {
        System.out.println();
        System.out.println("What is the minimum capacity you want to search for? (Type: a positive whole number)");
        String targetCapString = safeNext(in);
        while (!(isGreaterOrEqualZeroInt(targetCapString))) {
            printInvalidInputError();
            targetCapString = safeNext(in);
        }
        int targetCap = Integer.parseInt(targetCapString);
        ArrayList<Destination> biggestDestinations = new ArrayList<>();
        for (Continent continent : CONTINENTS) {
            biggestDestinations.addAll(continent.returnContinentDestinationsByCapacity(targetCap));
        }
        if (biggestDestinations.size() > 0) {
            System.out.println("The destinations with a capacity larger than " + targetCap + " are:");
            for (Destination destination : biggestDestinations) {
                System.out.println(capitaliseFirstLetters(destination.getName()) + " (capacity: " + destination.getCapacity() + ")");
            }
        } else {
            System.out.println("There are no destinations with a capacity larger than " + targetCap
                    + ". Try adding countries and destinations from the main menu.");
        }

    }

    //Method used if there are no results to display
    private static void printNoResults() {
        System.out.println("There are no results to display yet! Try adding countries and destinations from the main menu.");
    }

    //Method prints countries on the system, used if user wants to add a destination to a country not on the system
    private static void printAllCountries() {
        for (Continent continent : CONTINENTS) {
            for (Country country : continent.getAllCountries()) {
                System.out.println(capitaliseFirstLetters(country.getName()));
            }
        }
    }

    //Finds a country by name in any continent
    private static Country getCountryFromAllContinents(String countryName) {
        Country countryFound = null;
        for (Continent continent : CONTINENTS) {
            if (!(continent.returnCountryByName(countryName) == null)) {
                countryFound = continent.returnCountryByName(countryName);
                break;
            }
        }
        return countryFound;
    }

    //Method to print continent names when user is adding a country
    private static void printContinentNames() {
        for (Continent continent : CONTINENTS) {
            System.out.println(capitaliseFirstLetters(continent.getName()));
        }
    }

    //Returns the continent searched for
    private static Continent returnContinentByName(String desiredName) {
        for (Continent continent : CONTINENTS) {
            if (desiredName.equals(continent.getName())) {
                return continent;
            }
        }
        return null;
    }

    //Method to print invalid input error message
    private static void printInvalidInputError() {
        System.out.println("Input invalid, please try again!");
    }

    //Method asks for input and returns String in lowerCase
    private static String safeNext(Scanner in) {
        String answer = in.nextLine();
        return answer.toLowerCase();
    }

    //Method to check if String only contains letters, if only letters returns True
    private static Boolean isOnlyLetters(String str) {
        return str.matches("[a-z A-Z]+");
    }

    //Method to capitalise first letter of each word
    private static String capitaliseFirstLetters(String str) {
        char[] chars = str.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i])) {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    //Method checks if String is an integer 0 or higher and returns true or false
    private static boolean isGreaterOrEqualZeroInt(String str) {
        try {
            Integer.parseInt(str);
            return Integer.parseInt(str) >= 0;
        } catch (NumberFormatException e) {
            if (str.matches("[0-9]+")){
                System.out.println("That is quite a large number.");
            }
            return false;
            }
    }

    private static final int LATITUDE_MIN = -90;
    private static final int LATITUDE_MAX = 90;
    private static final int LONGITUDE_MIN = -180;
    private static final int LONGITUDE_MAX = 180;

    //Method checks if String is a double and within the ranges of latitude and longitude, returns true or false
    private static boolean isLatOrLongDouble(String str, String latOrLong) {
        try {
            Double.parseDouble(str);
            if (latOrLong.equals(LATITUDE)) {
                if (Double.parseDouble(str) >= LATITUDE_MIN && Double.parseDouble(str) <= LATITUDE_MAX){
                    return true;
                }
            } else if (latOrLong.equals(LONGITUDE)) {
                if (Double.parseDouble(str) >= LONGITUDE_MIN && Double.parseDouble(str) <= LONGITUDE_MAX) {
                    return true;
                }
            }
            if (latOrLong.equals(LATITUDE)){
                System.out.println("Latitude must be between " + LATITUDE_MIN + " and " + LATITUDE_MAX);
            } else if (latOrLong.equals(LONGITUDE)){
                System.out.println("Longitude must be between " + LONGITUDE_MIN + " and " + LONGITUDE_MAX);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    //Method checks if there are countries in the system
    private static boolean thereAreCountries() {
        boolean trueOrFalse = false;
        for (Continent continent : CONTINENTS) {
            if (continent.getAllCountries().size() > 0) {
                trueOrFalse = true;
                break;
            }
        }
        return trueOrFalse;
    }
}

