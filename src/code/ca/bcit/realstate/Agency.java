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
    /**
     * A constant representing the first position in a property list.
     */
    public static final int FIRST_PROPERTY_LIST_POSITION = 0;

    /**
     * A constant representing the index of the first character in a string.
     */
    public static final double MIN_TOTAL_PROPERTY_VALUES = 0;

    private final String               name;
    private final Map<String, Property> properties;

    /**
     * Constructs a ca.bcit.realstate.Agency with the specified name.
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
    public final String getName()
    {
        return name;
    }

    /**
     * Adds a property to the agency's collection.
     *
     * @param property the property to add
     */
    public final void addProperty(final Property property)
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
    public final void removeProperty(final String propertyId)
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
    public final Property getProperty(final String propertyId)
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
    public final double getTotalPropertyValues()
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
     * @return a list of properties with swimming pools
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

        return propertiesWithPools;
    }

    /**
     * Retrieves properties within the specified price range.
     *
     * @param minPriceUsd the minimum price in USD
     * @param maxPriceUsd the maximum price in USD
     *
     * @return an array of properties within the given price range,
     *          or null if none found
     */
    public Property[] getPropertiesBetween(final double minPriceUsd,
                                           final double maxPriceUsd)
    {
        final List<Property> propertyList;
        final Property[] propertyArray;
        final boolean    emptyArrayList;

        propertyList = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.getPriceUsd() >= minPriceUsd &&
               property.getPriceUsd() <= maxPriceUsd)
            {
                propertyList.add(property);
            }
        }

        emptyArrayList = properties.isEmpty();

        if(emptyArrayList)
        {
            return null;
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
        final boolean            emptyArrayList;

        matchAddresses = new ArrayList<>();

        for(final Property property : properties.values())
        {
            final boolean propertyFound;

            propertyFound = property.getAddress().
                                    getStreetName().
                                    equalsIgnoreCase(streetName);
            if(propertyFound)
            {
                matchAddresses.add(property.getAddress());
            }
        }

        emptyArrayList = matchAddresses.isEmpty();

        if(emptyArrayList)
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
        final HashMap<String, Residence> residenceProperties;
        residenceProperties = new HashMap<>();

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

    /**
     * Get a list of commercial properties with loading docks.
     *
     * @return a list of commercial properties with loading docks.
     */
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

                if(commercial.hasLoadingDock())
                {
                    commercialProperties.add(commercial);
                }
            }
        }

        return commercialProperties;

    }

    /**
     * Get a list of commercial properties with highway access.
     *
     * @return a list of commercial properties with highway access.
     */
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

                if(commercial.hasHighwayAccess())
                {
                    commercialProperties.add(commercial);
                }
            }
        }

        return commercialProperties;

    }

    /**
     * Filters and retrieves a list of Retail properties with a square footage
     * greater than or equal to the specified value.
     *
     * @param squareFootage the minimum square footage to filter Retail properties
     *
     * @return a list of Retail properties matching the square footage criteria,
     *         or null if no such properties are found
     */
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

                if(retail.getSquareFootage() >= squareFootage)
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


    /**
     * Retrieves a list of Retail properties that offer customer parking.
     *
     * @return a list of Retail properties with customer parking,
     *         or null if no such properties are found
     */
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

                if(retail.hasCustomerParking())
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

    /**
     * Get a list of residence in a strata.
     *
     * @return a list of residence in a strata.
     */
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

                if(residence.hasStrata())
                {
                    residenceProperties.add(residence);
                }
            }
        }

        return residenceProperties;
    }

}
