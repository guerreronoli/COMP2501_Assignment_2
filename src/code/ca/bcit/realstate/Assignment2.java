package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2
{
    private static final String ADDRESS_DATA_FILE = "address_data.txt";
    private static final String PROPERTY_DATA_FILE = "property_data.txt";

    //Property price constant
    public static final int PRICE_USD_INDEX = 0;

    //Residential constants
    public static final int RES_NUMBER_OF_BEDROOM_INDEX = 1;
    public static final int RES_SWIMMING_POOL_INDEX = 2;
    public static final int RES_TYPE_INDEX = 3;
    public static final int RES_PROPERTY_ID_INDEX = 4;
    public static final int RES_STRATA_INDEX = 5;

    //Commercial constants
    public static final int COMM_TYPE_INDEX = 1;
    public static final int COMM_PROPERTY_ID_INDEX = 2;
    public static final int COMM_LOADING_DOCK_INDEX = 3;
    public static final int COMM_HIGHWAY_ACCESS_INDEX = 4;

    //Retail constants
    public static final int RET_TYPE_INDEX = 1;
    public static final int RET_PROPERTY_ID_INDEX = 2;
    public static final int RET_SQUARE_FOOTAGE_INDEX = 3;
    public static final int RET_CUSTOMER_PARKING_INDEX = 4;

    public static final String REGEX_DELIMETER = "\\|";

    public static final String RESIDENTIAL_PROPERTY_TEXT = "residence";
    public static final String COMMERCIAL_PROPERTY_TEXT = "commercial";
    public static final String RETAIL_PROPERTY_TEXT = "retail";

    public static final String GENERAL_QUERIES_OPTION = "1";
    public static final String RESIDENCE_QUERIES_OPTION = "2";
    public static final String COMMERCIAL_QUERIES_OPTION = "3";
    public static final String RETAIL_QUERIES_OPTION = "4";
    public static final String EXIT_PROGRAM_OPTION = "5";

    private static final String GENERAL_QUERY_BY_PROPERTY_ID = "1";
    private static final String GENERAL_QUERY_BY_PRICE = "2";
    private static final String GENERAL_QUERY_BY_STREET = "3";
    private static final String GENERAL_QUERY_BY_TYPE = "4";
    private static final String GENERAL_BACK = "5";




    private final Scanner scanner;
    private final Agency agency;

    public Assignment2(final Agency agency)
    {
        this.agency = agency;
        scanner = new Scanner(System.in);
    }

    public static void main(final String[] args)
    {
        final Assignment2 a2;
        final Agency agency;

        agency = new Agency("Property R Us");
        a2 = new Assignment2(agency);

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

    public void init()
            throws FileNotFoundException
    {
        ArrayList<Address> addresses = AddressReader.readAddressData(new File(ADDRESS_DATA_FILE));

        ArrayList<String> propertyData = PropertyReader.readPropertyData(new File(PROPERTY_DATA_FILE));

        for (int i = 0; i < propertyData.size(); i++)
        {
            final String line;
            final String[] rawData;

            line = propertyData.get(i);
            rawData = line.split(REGEX_DELIMETER);

            if (rawData[RES_TYPE_INDEX].equalsIgnoreCase(RESIDENTIAL_PROPERTY_TEXT))
            {
                final Residence residence;
                residence = createResidenceProperty(rawData,
                                                    addresses.get(i));

                agency.addProperty(residence);
            }
            else if (rawData[COMM_TYPE_INDEX].equalsIgnoreCase(COMMERCIAL_PROPERTY_TEXT))
            {
                final Commercial commercial;
                commercial = createCommercialProperty(rawData,
                                                      addresses.get(i));

                agency.addProperty(commercial);
            }
            else if (rawData[RET_TYPE_INDEX].equalsIgnoreCase(RETAIL_PROPERTY_TEXT))
            {
                final Retail retail;
                retail = createRetailProperty(rawData,
                                              addresses.get(i));

                agency.addProperty(retail);
            }

        }

    }

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
            System.out.print(">> ");

            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase(GENERAL_QUERIES_OPTION))
            {
                //call generalQueries()
                generalQueries();
            }
            else if (choice.equalsIgnoreCase(RESIDENCE_QUERIES_OPTION))
            {
                //call residenceQueries()
                System.out.println("Residence Queries");
                residenceQueries();
            }
            else if (choice.equalsIgnoreCase(COMMERCIAL_QUERIES_OPTION))
            {
                //call commercialQueries()
                System.out.println("Commercial Queries");
            }
            else if (choice.equalsIgnoreCase(RETAIL_QUERIES_OPTION))
            {
                //call retailQueries()
                System.out.println("Retail Queries");
            }
            else
            {
                break;
            }
        }


    }

    private void generalQueries()
    {
        String choice;

        while(true)
        {
            System.out.println("General Queries");
            System.out.printf("%s. By Property ID\n",
                              GENERAL_QUERY_BY_PROPERTY_ID);
            System.out.printf("%s. By Price\n",
                              GENERAL_QUERY_BY_PRICE);
            System.out.printf("%s. By Street\n",
                              GENERAL_QUERY_BY_STREET);
            System.out.printf("%s. By Type\n",
                              GENERAL_QUERY_BY_TYPE);
            System.out.printf("%s. Back\n",
                              GENERAL_BACK);
            System.out.print(">> ");
            choice = scanner.nextLine();

            if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_PROPERTY_ID))
            {
                final String   propertyId;
                final Property property;
                final String   propertyDetails;

                System.out.print("Enter Property ID: ");
                propertyId = scanner.nextLine();

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
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_PRICE))
            {
                System.out.println("Querying by price...");
            }
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_STREET))
            {
                System.out.println("Querying by street name...");
            }
            else if(choice.equalsIgnoreCase(GENERAL_QUERY_BY_TYPE))
            {
                System.out.println("Querying by property type...");
            }
            else if(choice.equalsIgnoreCase(GENERAL_BACK))
            {
                return;
            }
        }
    }

    private void residenceQueries()
    {
        String choice;
        while (true)
        {
            System.out.println("1. Back");
            System.out.print(">> ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("1"))
            {
                return;
            }
        }

    }

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
        propertyType     = rawData[RES_TYPE_INDEX];
        propertyId       = rawData[RES_PROPERTY_ID_INDEX];

        //Residence fields
        numberOfBedrooms = Integer.parseInt(rawData[RES_NUMBER_OF_BEDROOM_INDEX]);
        swimmingPool     = Boolean.parseBoolean(rawData[RES_SWIMMING_POOL_INDEX]);
        strata           = Boolean.parseBoolean(rawData[RES_STRATA_INDEX]);

        residence = new Residence(priceUsd,
                                  address,
                                  numberOfBedrooms,
                                  swimmingPool,
                                  propertyType,
                                  propertyId,
                                  strata);

        return residence;
    }

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
        propertyType = rawData[COMM_TYPE_INDEX];
        propertyId   = rawData[COMM_PROPERTY_ID_INDEX];

        //Commercial fields
        loadingDock   = Boolean.parseBoolean(rawData[COMM_LOADING_DOCK_INDEX]  );
        highwayAccess = Boolean.parseBoolean(rawData[COMM_HIGHWAY_ACCESS_INDEX]);

        commercial = new Commercial(priceUsd,
                                    address,
                                    propertyType,
                                    propertyId,
                                    loadingDock,
                                    highwayAccess);

        return commercial;
    }

    private Retail createRetailProperty(final String[] rawData,
                                        final Address address)
    {
        final Retail retail;
        retail = new Retail(Double.parseDouble(rawData[PRICE_USD_INDEX]),
                            address,
                            rawData[RET_TYPE_INDEX],
                            rawData[RET_PROPERTY_ID_INDEX],
                            Integer.parseInt(rawData[RET_SQUARE_FOOTAGE_INDEX]),
                            Boolean.parseBoolean(rawData[RET_CUSTOMER_PARKING_INDEX]));

        return retail;
    }
}
