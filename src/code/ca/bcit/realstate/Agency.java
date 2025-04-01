package ca.bcit.realstate;

import java.util.*;

/**
 * Represents a real estate agency that manages a collection of properties.
 * Provides functionality for adding, removing, and querying properties based on various criteria.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class Agency
{
    public static final String SINGLE_SPACE = " ";

    public static final int FIRST_PROPERTY_LIST_POSITION = 0;

    public static final int FIRST_STRING_CHARACTER  = 0;
    public static final int SECOND_STRING_CHARACTER = 1;

    public static final double MIN_TOTAL_PROPERTY_VALUES = 0;

    private final String               name;
    private final Map<String, Property> properties;

    /**
     * Constructs an ca.bcit.realstate.Agency with the specified name.
     *
     * @param name the name of the agency
     */
    public Agency(final String name)
    {
        this.name  = name;
        properties = new HashMap<>();
    }

    /**
     * Retrieves the name of the agency.
     *
     * @return the name of the agency
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds a property to the agency's collection.
     *
     * @param property the property to add
     */
    public void addProperty(final Property property)
    {
        if(property != null)
        {
            properties.put(property.getPropertyId(),property);
        }
    }


    /**
     * Removes a property from the agency's collection by its property ID.
     *
     * @param propertyId the ID of the property to remove
     */
    public void removeProperty(final String propertyId)
    {
        if(propertyId != null)
        {
            properties.remove(propertyId);
        }
    }


    /**
     * Retrieves a property by its property ID.
     *
     * @param propertyId the ID of the property to retrieve
     *
     * @return the property with the given ID, or null if not found
     */
    public Property getProperty(final String propertyId)
    {
        if(propertyId == null)
        {
            return null;
        }

        final Property property;
        property = properties.get(propertyId);

        return property;
    }


    /**
     * Calculates the total value of all properties in USD.
     *
     * @return the total value of all properties
     */
    public double getTotalPropertyValues()
    {
        double totalPropertyValues;
        totalPropertyValues = MIN_TOTAL_PROPERTY_VALUES;

        for(final Property property : properties.values())
        {
            totalPropertyValues += property.getPriceUsd();

        }

        return totalPropertyValues;
    }

    /**
     * Retrieves properties that have swimming pools.
     *
     * @return a list of properties with swimming pools, or null if none found
     */
    public ArrayList<Residence> getPropertiesWithPools()
    {
        final ArrayList<Residence> propertiesWithPools;
        propertiesWithPools = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof Residence)
            {
                final Residence residence;
                residence = (Residence) property;

                if(residence.hasSwimmingPool())
                {
                    propertiesWithPools.add(residence);
                }
            }
        }

        if(propertiesWithPools.isEmpty())
        {
            return null;
        }
        else
        {
            return propertiesWithPools;
        }
    }

    /**
     * Retrieves properties within the specified price range.
     *
     * @param minPriceUsd the minimum price in USD
     * @param maxPriceUsd the maximum price in USD
     *
     * @return an array of properties within the given price range
     */
    public Property[] getPropertiesBetween(final double minPriceUsd,
                                           final double maxPriceUsd)
    {
        final List<Property> propertyList;
        final Property[] propertyArray;

        propertyList = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.getPriceUsd() >= minPriceUsd &&
               property.getPriceUsd() <= maxPriceUsd)
            {
                propertyList.add(property);
            }
        }

        propertyArray = propertyList.toArray(new Property[FIRST_PROPERTY_LIST_POSITION]);

        return propertyArray;
    }


    /**
     * Retrieves addresses of properties located on a specific street.
     *
     * @param streetName the name of the street
     *
     * @return a list of addresses on the given street,
     *         or null if none found
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        final ArrayList<Address> matchAddresses;
        matchAddresses = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.getAddress().getStreetName().equalsIgnoreCase(streetName))
            {
                matchAddresses.add(property.getAddress());
            }
        }

        if(matchAddresses.isEmpty())
        {
            return null;
        }
        else
        {
            return matchAddresses;
        }
    }


    /**
     * Retrieves properties within a specified bedroom range.
     *
     * @param minBedrooms the minimum number of bedrooms
     * @param maxBedrooms the maximum number of bedrooms
     *
     * @return a map of property IDs to properties that meet the bedroom criteria,
     *         or null if none found
     */
    public HashMap<String, Residence> getPropertiesWithBedrooms(final int minBedrooms,
                                                               final int maxBedrooms)
    {
        final HashMap<String, Residence> residenceProperties = new HashMap<>();

        for(final Property property : properties.values())
        {
            if(property instanceof Residence)
            {
                final Residence residence;
                residence = (Residence) property;

                if(residence.getNumberOfBedrooms() >= minBedrooms &&
                        residence.getNumberOfBedrooms() <= maxBedrooms)
                {
                    residenceProperties.put(property.getPropertyId(),residence);
                }
            }
        }

        if(residenceProperties.isEmpty())
        {
            return null;
        }
        else
        {
            return residenceProperties;
        }
    }

    /**
     * Retrieves a list of property details of a specific type.
     *
     * @param propertyType the type of property to search for
     *
     * @return a list of property details matching the specified type
     */
    public ArrayList<Property> getPropertiesOfType(final String propertyType)
    {
        final ArrayList<Property> properties;
        properties = new ArrayList<>();

        for(final Property property : this.properties.values())
        {
            if(property.getType().equalsIgnoreCase(propertyType))
            {
                properties.add(property);
            }
        }

        return properties;

    }

    public ArrayList<Commercial> getPropertiesWithLoadingDocks()
    {
        final ArrayList<Commercial> commercialProperties;
        commercialProperties = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof Commercial)
            {
                final Commercial commercial;
                commercial = (Commercial) property;

                if(commercial.loadingDock())
                {
                    commercialProperties.add(commercial);
                }
            }
        }

        return commercialProperties;

    }

    public ArrayList<Commercial> getPropertiesWithHighwayAccess()
    {
        final ArrayList<Commercial> commercialProperties;
        commercialProperties = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof Commercial)
            {
                final Commercial commercial;
                commercial = (Commercial) property;

                if(commercial.highwayAccess())
                {
                    commercialProperties.add(commercial);
                }
            }
        }

        if(commercialProperties.isEmpty())
        {
            return null;
        }

        return commercialProperties;

    }

    public ArrayList<Retail> getPropertiesSquareFootage(final int squareFootage)
    {
        final ArrayList<Retail> retailProperties;
        retailProperties = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof  Retail)
            {
                final Retail retail;
                retail = (Retail) property;

                if(retail.squareFootage() >= squareFootage)
                {
                    retailProperties.add(retail);
                }
            }
        }

        if(retailProperties.isEmpty())
        {
            return null;
        }

        return retailProperties;
    }

    public ArrayList<Retail> getPropertiesWithCustomerParking()
    {
        final ArrayList<Retail> retailProperties;
        retailProperties = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof  Retail)
            {
                final Retail retail;
                retail = (Retail) property;

                if(retail.customerParking())
                {
                    retailProperties.add(retail);
                }
            }
        }

        if(retailProperties.isEmpty())
        {
            return null;
        }

        return retailProperties;
    }

    public ArrayList<Residence> getPropertiesWithStrata()
    {
        final ArrayList<Residence> residenceProperties;
        residenceProperties = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property instanceof  Residence)
            {
                final Residence residence;
                residence = (Residence) property;

                if(residence.strata())
                {
                    residenceProperties.add(residence);
                }
            }
        }

        if(residenceProperties.isEmpty())
        {
            return null;
        }

        return residenceProperties;
    }

    /*
     * Formats property details into a readable string.
     *
     * @param property the property object
     * @param address  the address of the property
     *
     * @return formatted property details
     */
//    private String getPropertyDetails(Property property, Address address)
//    {
//        final String unitNumber      = address.getUnitNumber() == null ? "":
//                                        " unit #" + address.getUnitNumber() + " at";
//        final int    streetNumber    = address.getStreetNumber();
//        final String postalCode      = address.getPostalCode().toUpperCase();
//
//        final String streetName      = getProperString(address.getStreetName());
//        final String city            = getProperString(address.getCity());
//        final String propertyId      = property.getPropertyId();
//        final String numberOfBedroom = property.getNumberOfBedrooms() +
//                                        (property.getNumberOfBedrooms() == Property.MIN_NUMBER_OF_BEDROOMS ?
//                                                " bedroom" : " bedrooms");
//        final double priceUsd        = property.getPriceUsd();
//        final String hasPool         = property.hasSwimmingPool() ? " plus pool":"";
//
//        return String.format(") ca.bcit.realstate.Property %s:%s %d %s %s in %s (%s%s): $%.0f.\n",
//                             propertyId,
//                             unitNumber,
//                             streetNumber,
//                             streetName,
//                             postalCode,
//                             city,
//                             numberOfBedroom,
//                             hasPool,
//                             priceUsd);
//    }

    /*
     * Converts a string to proper case format.
     *
     * @param value the string to format
     * @return the formatted string
     */
    private String getProperString(final String value)
    {
        final String[] words = value.toLowerCase().split(SINGLE_SPACE);

        final int lastWordIndex;
        lastWordIndex = words.length - 1;

        StringBuilder properString = new StringBuilder();

        for(int i = 0; i < words.length; i++)
        {
            properString.append(Character.toUpperCase(words[i].charAt(FIRST_STRING_CHARACTER)));
            properString.append(words[i].substring(SECOND_STRING_CHARACTER));

            //If it's not the last word, add a space
            if(i != lastWordIndex)
            {
                properString.append(SINGLE_SPACE);
            }
        }

        return properString.toString();
    }


}
