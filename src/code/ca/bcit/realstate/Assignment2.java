package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents an Assignment2 object which is a driver class
 * for the real state application. This is the main entry point of the program.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class Assignment2
{
    private static final String ADDRESS_DATA_FILE  = "address_data.txt";
    private static final String PROPERTY_DATA_FILE = "property_data.txt";

    private static final String INVALID_OPTION = "Invalid Option!";

    //Property price constant
    private static final int PRICE_USD_INDEX       = 0;
    private static final int BACK_TO_PREVIOUS_MENU = 0;

    //Residential data file indexes
    private static final int RESIDENCE_NUMBER_OF_BEDROOM_INDEX = 1;
    private static final int RESIDENCE_SWIMMING_POOL_INDEX     = 2;
    private static final int RESIDENCE_TYPE_INDEX              = 3;
    private static final int RESIDENCE_PROPERTY_ID_INDEX       = 4;
    private static final int RESIDENCE_STRATA_INDEX            = 5;

    //Commercial data file indexes
    private static final int COMMERCIAL_TYPE_INDEX           = 1;
    private static final int COMMERCIAL_PROPERTY_ID_INDEX    = 2;
    private static final int COMMERCIAL_LOADING_DOCK_INDEX   = 3;
    private static final int COMMERCIAL_HIGHWAY_ACCESS_INDEX = 4;

    //Retail data file indexes
    private static final int RETAIL_TYPE_INDEX             = 1;
    private static final int RETAIL_PROPERTY_ID_INDEX      = 2;
    private static final int RETAIL_SQUARE_FOOTAGE_INDEX   = 3;
    private static final int RETAIL_CUSTOMER_PARKING_INDEX = 4;

    private static final String REGEX_DELIMETER = "\\|";

    private static final String RESIDENTIAL_PROPERTY_TEXT = "residence";
    private static final String COMMERCIAL_PROPERTY_TEXT  = "commercial";
    private static final String RETAIL_PROPERTY_TEXT      = "retail";

    //main menu options
    private static final String GENERAL_QUERIES_OPTION    = "1";
    private static final String RESIDENCE_QUERIES_OPTION  = "2";
    private static final String COMMERCIAL_QUERIES_OPTION = "3";
    private static final String RETAIL_QUERIES_OPTION     = "4";
    private static final String EXIT_PROGRAM_OPTION       = "5";

    //general queries menu options
    private static final String GENERAL_QUERY_BY_PROPERTY_ID_OPTION = "1";
    private static final String GENERAL_QUERY_BY_PRICE_OPTION       = "2";
    private static final String GENERAL_QUERY_BY_STREET_OPTION      = "3";
    private static final String GENERAL_QUERY_BY_TYPE_OPTION        = "4";
    private static final String GENERAL_BACK_OPTION                 = "5";

    //residence queries menu options
    private static final String RESIDENCE_QUERY_BY_BEDROOM_OPTION = "1";
    private static final String RESIDENCE_QUERY_BY_POOL_OPTION    = "2";
    private static final String RESIDENCE_QUERY_BY_STRATA_OPTION  = "3";
    private static final String RESIDENCE_QUERY_BACK_OPTION       = "4";

    //commercial queries menu options
    private static final String COMMERCIAL_QUERY_BY_LOADING_DOCK_OPTION = "1";
    private static final String COMMERCIAL_QUERY_BY_HIGHWAY_OPTION      = "2";
    private static final String COMMERCIAL_QUERY_BACK_OPTION            = "3";

    //retail queries menu options
    private static final String RETAIL_QUERY_BY_SQUARE_FOOTAGE_OPTION   = "1";
    private static final String RETAIL_QUERY_BY_CUSTOMER_PARKING_OPTION = "2";
    private static final String RETAIL_QUERY_BACK_OPTION                = "3";


    private static final int EMPTY_ARRAY = 0;
    private static final String PREVIOUS_MENU = "x";


    private final Scanner scanner;
    private final Agency agency;

    /**
     * Construct Assignment2 object.
     *
     * @param agency the agency object.
     */
    public Assignment2(final Agency agency)
    {
        this.agency = agency;
        scanner     = new Scanner(System.in);
    }

    public static void main(final String[] args)
    {
        final Assignment2 a2;
        final Agency      agency;

        agency = new Agency("Property R Us");
        a2     = new Assignment2(agency);

        try
        {
            a2.init();
            a2.doSearches();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * initialize agency object with initial data.
     *
     * @throws FileNotFoundException if file is not found.
     */
    public void init()
            throws FileNotFoundException
    {
        ArrayList<Address> addresses = AddressReader.readAddressData(new File(ADDRESS_DATA_FILE));

        ArrayList<String> propertyData = PropertyReader.readPropertyData(new File(PROPERTY_DATA_FILE));

        for (int i = 0; i < propertyData.size(); i++)
        {
            final String line;
            final String[] rawData;

            line    = propertyData.get(i);
            rawData = line.split(REGEX_DELIMETER);

            if (rawData[RESIDENCE_TYPE_INDEX].equalsIgnoreCase(RESIDENTIAL_PROPERTY_TEXT))
            {
                final Residence residence;

                residence = createResidenceProperty(rawData,
                                                    addresses.get(i));
                agency.addProperty(residence);
            }
            else if (rawData[COMMERCIAL_TYPE_INDEX].equalsIgnoreCase(COMMERCIAL_PROPERTY_TEXT))
            {
                final Commercial commercial;

                commercial = createCommercialProperty(rawData,
                                                      addresses.get(i));
                agency.addProperty(commercial);
            }
            else if (rawData[RETAIL_TYPE_INDEX].equalsIgnoreCase(RETAIL_PROPERTY_TEXT))
            {
                final Retail retail;

                retail = createRetailProperty(rawData,
                                              addresses.get(i));
                agency.addProperty(retail);
            }

        }

    }

    /**
     * Main menu of the program.
     */
    public void doSearches()
    {
        String choice;

        while (true)
        {
            System.out.println("Welcome to our Property search");
            System.out.println("Choose one of the following options:");
            System.out.printf("%s. General Queries\n",
                              GENERAL_QUERIES_OPTION);
            System.out.printf("%s. Residence Queries\n",
                              RESIDENCE_QUERIES_OPTION);
            System.out.printf("%s. Commercial Queries\n",
                              COMMERCIAL_QUERIES_OPTION);
            System.out.printf("%s. Retail Queries\n",
                              RETAIL_QUERIES_OPTION);
            System.out.printf("%s. Exit\n",
                              EXIT_PROGRAM_OPTION);

            choice = getUserInput(">> ");

            if (choice.equalsIgnoreCase(GENERAL_QUERIES_OPTION))
            {
                //call generalQueries()
                generalQueries();
            }
            else if (choice.equalsIgnoreCase(RESIDENCE_QUERIES_OPTION))
            {
                //call residenceQueries()
                residenceQueries();
            }
            else if (choice.equalsIgnoreCase(COMMERCIAL_QUERIES_OPTION))
            {
                //call commercialQueries()
                commercialQueries();

            }
            else if (choice.equalsIgnoreCase(RETAIL_QUERIES_OPTION))
            {
                //call retailQueries()
                retailQueries();
            }
            else if(choice.equalsIgnoreCase(EXIT_PROGRAM_OPTION))
            {
                break;
            }
            else
            {
                System.out.println(INVALID_OPTION);
            }
        }


    }

    /**
     * Method for looking up properties regardless of its type.
     * Display menu in general related to property and
     * the do query from agency object.
     */
    private void generalQueries()
    {
        String   choice;
        String   propertyDetails;

        while(true)
        {
            System.out.println("General Queries");
            System.out.printf("%s. By Property ID\n",
                              GENERAL_QUERY_BY_PROPERTY_ID_OPTION);
            System.out.printf("%s. By Price\n",
                              GENERAL_QUERY_BY_PRICE_OPTION);
            System.out.printf("%s. By Street\n",
                              GENERAL_QUERY_BY_STREET_OPTION);
            System.out.printf("%s. By Type\n",
                              GENERAL_QUERY_BY_TYPE_OPTION);
            System.out.printf("%s. Back\n",
                              GENERAL_BACK_OPTION);

            choice = getUserInput(">> ");

            if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_PROPERTY_ID_OPTION))
            {
                final String propertyId;
                final Property property;

                System.out.println("Querying by property...");
                propertyId = getUserInput("Enter Property ID: ");

                if(propertyId != null)
                {
                    property = agency.getProperty(propertyId);

                    if(property != null)
                    {
                        propertyDetails = property.toString();
                        System.out.println(propertyDetails);
                    }
                    else
                    {
                        System.out.println("Property not found!");
                    }
                }
            }
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_PRICE_OPTION))
            {
                final double     minPrice;
                final double     maxPrice;
                final Property[] properties;

                System.out.println("Querying by price...");

                minPrice = getPriceInput("Enter minimum price");

                /*
                 * If the minPrice is equal to BACK_TO_PREVIOUS_MENU
                 * that means the user decided to go back to the previous menu
                 */
                if(minPrice == BACK_TO_PREVIOUS_MENU)
                {
                    continue;
                }

                /*
                 * If the maxPrice is equal to BACK_TO_PREVIOUS_MENU
                 * that means the user decided to go back to the previous menu
                 */
                maxPrice = getPriceInput("Enter maximum price");
                if(maxPrice == BACK_TO_PREVIOUS_MENU)
                {
                    continue;
                }

                properties = agency.getPropertiesBetween(minPrice,
                                                         maxPrice);
                if(properties.length != EMPTY_ARRAY)
                {
                    for(final Property propertyResult : properties)
                    {
                        propertyDetails = propertyResult.toString();
                        System.out.println(propertyDetails);
                    }
                }
                else
                {
                    System.out.printf("No properties found on a price range of $%.1f and $%.1f.\n",
                                      minPrice,
                                      maxPrice);
                }

            }
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_STREET_OPTION))
            {
                final String streetName;
                final ArrayList<Address> addresses;

                System.out.println("Querying by street name...");
                streetName = getUserInput("Enter street name: ");

                addresses = agency.getPropertiesOn(streetName);

                if(addresses != null)
                {
                    for (final Address address : addresses)
                    {
                        propertyDetails = address.toString();
                        System.out.println(propertyDetails);
                    }
                }
                else
                {
                    System.out.println("No properties found with a street name " + streetName);
                }

            }
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_TYPE_OPTION))
            {
                final String              propertyType;
                final ArrayList<Property> properties;

                System.out.println("Querying by property type...");

                propertyType = getUserInput(String.format("Enter property type %s: ",
                                                          Arrays.asList(Property.PROPERTY_TYPE)));

                properties = agency.getPropertiesOfType(propertyType);

                if(properties.isEmpty())
                {
                    System.out.println("No properties found!");
                    continue;
                }

                for(final Property property : properties)
                {
                    propertyDetails = property.toString();
                    System.out.println(propertyDetails);
                }

            }
            else if(choice.equalsIgnoreCase(GENERAL_BACK_OPTION))
            {
                return;
            }
            else
            {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /**
     * Method for looking up Residence properties.
     * Display menu specific for residence property and
     * the do query from agency object.
     */
    private void residenceQueries()
    {
        String choice;

        while (true)
        {
            System.out.println("Residence Queries");
            System.out.printf("%s. By Bedroom\n",
                              RESIDENCE_QUERY_BY_BEDROOM_OPTION);
            System.out.printf("%s. By Pool\n",
                              RESIDENCE_QUERY_BY_POOL_OPTION);
            System.out.printf("%s. By Strata\n",
                              RESIDENCE_QUERY_BY_STRATA_OPTION);
            System.out.printf("%s. Back\n",
                              RESIDENCE_QUERY_BACK_OPTION);
            choice = getUserInput(">> ");

            if(choice.equalsIgnoreCase(RESIDENCE_QUERY_BY_BEDROOM_OPTION))
            {
                System.out.printf("Querying %s by number of bedroom...\n",
                                  RESIDENTIAL_PROPERTY_TEXT);

                final int                    minNumberOfBedroom;
                final int                    maxNumberOfBedroom;
                final Map<String, Residence> residences;

                minNumberOfBedroom = getIntegerInput("Enter minimum number of bedroom");

                /*
                 * If the minPrice is equal to BACK_TO_PREVIOUS_MENU
                 * that means the user decided to go back to the previous menu
                 */
                if(minNumberOfBedroom == BACK_TO_PREVIOUS_MENU)
                {
                    continue;
                }

                /*
                 * If the maxPrice is equal to BACK_TO_PREVIOUS_MENU
                 * that means the user decided to go back to the previous menu
                 */
                maxNumberOfBedroom = getIntegerInput("Enter maximum number of bedroom");
                if(maxNumberOfBedroom == BACK_TO_PREVIOUS_MENU)
                {
                    continue;
                }

                residences = agency.getPropertiesWithBedrooms(minNumberOfBedroom,
                                                              maxNumberOfBedroom);

                if(residences != null && !residences.isEmpty())
                {
                    for(final Residence residence : residences.values())
                    {
                        System.out.println(residence.toString());
                    }
                }
                else
                {
                    System.out.printf("No properties found with %d to %d bedrooms.\n",
                                      minNumberOfBedroom,
                                      maxNumberOfBedroom);
                }

            }
            else if(choice.equalsIgnoreCase(RESIDENCE_QUERY_BY_POOL_OPTION))
            {
                System.out.printf("Querying %s with pool...\n",
                                  RESIDENTIAL_PROPERTY_TEXT);

                final List<Residence> residencesWithPool;

                residencesWithPool = agency.getPropertiesWithPools();

                if(residencesWithPool.isEmpty())
                {
                    System.out.println("No residences found with a pool!");
                    continue;
                }

                for(final Residence residence : residencesWithPool)
                {
                    System.out.println(residence.toString());
                }

            }
            else if(choice.equalsIgnoreCase(RESIDENCE_QUERY_BY_STRATA_OPTION))
            {
                System.out.printf("Querying %s in strata...\n",
                                  RESIDENTIAL_PROPERTY_TEXT);

                final List<Residence> residencesInStrata;

                residencesInStrata = agency.getPropertiesWithStrata();

                if(residencesInStrata.isEmpty())
                {
                    System.out.println("No residences within strata!");
                }

                for(final Residence residence : residencesInStrata)
                {
                    System.out.println(residence.toString());
                }
            }
            else if(choice.equalsIgnoreCase(RESIDENCE_QUERY_BACK_OPTION))
            {
                return;
            }
            else
            {
                System.out.println(INVALID_OPTION);
            }

        }

    }

    /**
     * Method for looking up Commercial properties.
     * Display menu specific for commercial property and
     * the do query from agency object.
     */
    private void commercialQueries()
    {
        String choice;

        while (true)
        {
            System.out.println("Commercial Queries");
            System.out.printf("%s. By Loading Dock\n",
                              COMMERCIAL_QUERY_BY_LOADING_DOCK_OPTION);
            System.out.printf("%s. By Highway Access\n",
                              COMMERCIAL_QUERY_BY_HIGHWAY_OPTION);
            System.out.printf("%s. Back\n",
                              COMMERCIAL_QUERY_BACK_OPTION);
            choice = getUserInput(">> ");

            if(choice.equalsIgnoreCase(COMMERCIAL_QUERY_BY_LOADING_DOCK_OPTION))
            {

                final List<Commercial> propertiesWithLoadingDock;

                propertiesWithLoadingDock = agency.getPropertiesWithLoadingDocks();

                if(propertiesWithLoadingDock.isEmpty())
                {
                    System.out.println("No commercial properties with loading dock!");
                    continue;
                }

                for(final Commercial commercial : propertiesWithLoadingDock)
                {
                    System.out.println(commercial.toString());
                }
            }
            else if(choice.equalsIgnoreCase(COMMERCIAL_QUERY_BY_HIGHWAY_OPTION))
            {
                final List<Commercial> propertiesWithHighwayAccess;

                propertiesWithHighwayAccess = agency.getPropertiesWithHighwayAccess();

                if(propertiesWithHighwayAccess.isEmpty())
                {
                    System.out.println("No commercial properties with highway access!");
                }

                for(final Commercial commercial : propertiesWithHighwayAccess)
                {
                    System.out.println(commercial.toString());
                }
            }
            else if(choice.equalsIgnoreCase(COMMERCIAL_QUERY_BACK_OPTION))
            {
                return;
            }
            else
            {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /**
     * Method for looking up Retail properties.
     * Display menu specific for retail property and
     * the do query from agency object.
     */
    private void retailQueries()
    {
        String choice;

        while (true)
        {
            System.out.println("Retail Queries");
            System.out.printf("%s. By Square Footage\n",
                              RETAIL_QUERY_BY_SQUARE_FOOTAGE_OPTION);
            System.out.printf("%s. By Customer Parking\n",
                              RETAIL_QUERY_BY_CUSTOMER_PARKING_OPTION);
            System.out.printf("%s. Back\n",
                              RETAIL_QUERY_BACK_OPTION);
            choice = getUserInput(">> ");

            if(choice.equalsIgnoreCase(RETAIL_QUERY_BY_SQUARE_FOOTAGE_OPTION))
            {
                final List<Retail> retailsBySquareFootage;
                final int minSquareFoot;

                minSquareFoot = getIntegerInput("Enter minimum retail square footage");

                if(minSquareFoot == BACK_TO_PREVIOUS_MENU)
                {
                    continue;
                }

                retailsBySquareFootage = agency.getPropertiesSquareFootage(minSquareFoot);

                for(final Retail retail : retailsBySquareFootage)
                {
                    System.out.println(retail.toString());
                }
            }
            else if(choice.equalsIgnoreCase(RETAIL_QUERY_BY_CUSTOMER_PARKING_OPTION))
            {
                final List<Retail> retailsWithCustomerParking;

                retailsWithCustomerParking = agency.getPropertiesWithCustomerParking();

                for(final Retail retail : retailsWithCustomerParking)
                {
                    System.out.println(retail.toString());
                }
            }
            else if(choice.equalsIgnoreCase(RETAIL_QUERY_BACK_OPTION))
            {
                return;
            }
            else
            {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /**
     * Prompt user to get an input from the keyboard.
     *
     * @param messagePrompt the text to display on the screen.
     *
     * @return the user input.
     */
    private String getUserInput(final String messagePrompt)
    {
        final String input;

        System.out.print(messagePrompt);
        input = scanner.nextLine();

        return input;
    }


    /**
     * Prompt user to enter a price of the property.
     * Method will not exit until a valid number is entered
     * unless the user enter PREVIOUS_MENU to exit the function.
     *
     * @param messagePrompt the text to display on the screen.
     *
     * @return a double input from The user.
     */
    private double getPriceInput(final String messagePrompt)
    {
        String userInput;
        while(true)
        {
            userInput = getUserInput(String.format("%s or [%s] to previous menu: ",
                                                   messagePrompt,
                                                   PREVIOUS_MENU));
            if(isNumeric(userInput))
            {
                return Double.parseDouble(userInput);
            }
            else
            {
                if(userInput.equalsIgnoreCase(PREVIOUS_MENU))
                {
                    return BACK_TO_PREVIOUS_MENU;
                }
                else
                {
                    System.out.println("Invalid input: " + userInput);
                }
            }
        }
    }

    /**
     * Prompt user to enter a price of the property.
     * Method will not exit until a valid number is entered
     * unless the user enter PREVIOUS_MENU to exit the function.
     *
     * @param messagePrompt the text to display on the screen.
     *
     * @return a double input from The user.
     */
    private int getIntegerInput(final String messagePrompt)
    {
        String userInput;
        while(true)
        {
            userInput = getUserInput(String.format("%s or [%s] to previous menu: ",
                                                   messagePrompt,
                                                   PREVIOUS_MENU));
            if(isNumeric(userInput))
            {
                return Integer.parseInt(userInput);
            }
            else
            {
                if(userInput.equalsIgnoreCase(PREVIOUS_MENU))
                {
                    return BACK_TO_PREVIOUS_MENU;
                }
                else
                {
                    System.out.println("Invalid input: " + userInput);
                }
            }
        }
    }


    /**
     * Check if the string is a numeric
     *
     * @param numberToParse the string the parse.
     *
     * @return true if the text is a number,
     *          false otherwise
     */
    private boolean isNumeric(final String numberToParse)
    {
        try
        {
            Double.parseDouble(numberToParse);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }


    /**
     * Create a Residence object
     * which is a subtype of a Property class
     *
     * @param rawData a string line separated by @{REGEX_DELIMETER}
     * @param address the address object for the provided for a property.
     *
     * @return Residence object
     */
    private Residence createResidenceProperty(final String[] rawData,
                                              final Address  address)
    {
        final Residence residence;
        final double    priceUsd;
        final String    propertyType;
        final String    propertyId;

        final int     numberOfBedrooms;
        final boolean swimmingPool;
        final boolean strata;

        //Property's common fields
        priceUsd         = Double.parseDouble(rawData[PRICE_USD_INDEX]);
        propertyType     = rawData[RESIDENCE_TYPE_INDEX];
        propertyId       = rawData[RESIDENCE_PROPERTY_ID_INDEX];

        //Residence fields
        numberOfBedrooms = Integer.parseInt(rawData[RESIDENCE_NUMBER_OF_BEDROOM_INDEX]);
        swimmingPool     = Boolean.parseBoolean(rawData[RESIDENCE_SWIMMING_POOL_INDEX]);
        strata           = Boolean.parseBoolean(rawData[RESIDENCE_STRATA_INDEX]);

        residence = new Residence(priceUsd,
                                  address,
                                  numberOfBedrooms,
                                  swimmingPool,
                                  propertyType,
                                  propertyId,
                                  strata);

        return residence;
    }

    /**
     * Create a Commercial object
     * which is a subtype of a Property class
     *
     * @param rawData a string line separated by @{REGEX_DELIMETER}
     * @param address the address object for the provided for a property.
     *
     * @return Commercial object
     */
    private Commercial createCommercialProperty(final String[] rawData,
                                                final Address address)
    {
        final Commercial commercial;
        final double     priceUsd;
        final String     propertyType;
        final String     propertyId;

        final boolean loadingDock;
        final boolean highwayAccess;

        //Property's common fields
        priceUsd     = Double.parseDouble(rawData[PRICE_USD_INDEX]);
        propertyType = rawData[COMMERCIAL_TYPE_INDEX];
        propertyId   = rawData[COMMERCIAL_PROPERTY_ID_INDEX];

        //Commercial fields
        loadingDock   = Boolean.parseBoolean(rawData[COMMERCIAL_LOADING_DOCK_INDEX]);
        highwayAccess = Boolean.parseBoolean(rawData[COMMERCIAL_HIGHWAY_ACCESS_INDEX]);

        commercial = new Commercial(priceUsd,
                                    address,
                                    propertyType,
                                    propertyId,
                                    loadingDock,
                                    highwayAccess);

        return commercial;
    }

    /**
     * Create a Retail object
     * which is a subtype of a Property class
     *
     * @param rawData a string line separated by @{REGEX_DELIMETER}
     * @param address the address object for the provided for a property.
     *
     * @return Retail object
     */
    private Retail createRetailProperty(final String[] rawData,
                                        final Address address)
    {
        final Retail retail;
        retail = new Retail(Double.parseDouble(rawData[PRICE_USD_INDEX]),
                            address,
                            rawData[RETAIL_TYPE_INDEX],
                            rawData[RETAIL_PROPERTY_ID_INDEX],
                            Integer.parseInt(rawData[RETAIL_SQUARE_FOOTAGE_INDEX]),
                            Boolean.parseBoolean(rawData[RETAIL_CUSTOMER_PARKING_INDEX]));

        return retail;
    }
}
