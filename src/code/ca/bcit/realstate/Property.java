package ca.bcit.realstate;

/**
 * Represents a property with details such as price, address, number of bedrooms,
 * swimming pool availability, type, and property ID. Includes validation to ensure data integrity.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class Property
{
    public static final String[] PROPERTY_TYPE = {
            "residence",
            "commercial",
            "retail"
    };

    public static final int INVALID_PRICE = 0;
    public static final int MAX_PROPERTY_ID_CHARACTERS = 6;

    private final Address address;
    private final String  propertyId;
    private final String  type;

    private double priceUsd;

    /**
     * Constructs a Property object with the specified details.
     *
     * @param priceUsd         - the price of the property in USD (must be non-negative)
     * @param address          - the address of the property (cannot be null)
     * @param type             - the type of property (must be one of PROPERTY_TYPE values)
     * @param propertyId       - the unique identifier for the property (must be non-blank
     *                           and up to MAX_PROPERTY_ID_CHARACTERS characters)
     *
     * @throws IllegalArgumentException - if any parameter is invalid
     * @throws NullPointerException     - if address, type, or propertyId is null
     */
    public Property(final double  priceUsd,
                    final Address address,
                    final String  type,
                    final String  propertyId)
    {
        if(address == null)
        {
            throw  new NullPointerException("Invalid address: null");
        }

        /*
         * if property type is valid,
         * validatePropertyType function will throw an exception
         */
        validatePropertyType(type);

        /*
         * if property type is valid,
         * validatePropertyId function will throw an exception
         */
        validatePropertyId(propertyId);

        this.address          = address;
        this.type             = type;
        this.propertyId       = propertyId;

        setPriceUsd(priceUsd);
    }


    /**
     * Returns the price of the property in USD.
     *
     * @return the property price in USD
     */
    public final double getPriceUsd()
    {
        return priceUsd;
    }


    /**
     * Sets the price of the property in USD.
     *
     * @param priceUsd - the price in USD (must be non-negative)
     *
     * @throws IllegalArgumentException - if priceUsd is negative
     */
    protected void setPriceUsd(final double priceUsd)
    {
        if(priceUsd < INVALID_PRICE)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUsd);
        }

        this.priceUsd = priceUsd;
    }


    /**
     * Returns the address of the property.
     *
     * @return the property address
     */
    public final Address getAddress()
    {
        return address;
    }


    /**
     * Returns the type of the property.
     *
     * @return the property type
     */
    public final String getType()
    {
        return type;
    }


    /**
     * Returns the unique property ID.
     *
     * @return the property ID
     */
    public final String getPropertyId()
    {
        return propertyId;
    }

    @Override
    public String toString()
    {

        return String.format("Property[" +
                             "propertyId=%s, " +
                             "type=%s, " +
                             "priceUsd=%.1f, " +
                             "%s]",
                             propertyId,
                             type,
                             priceUsd,
                             address.toString());
    }

    /*
     * Validates the property type against predefined values.
     *
     * @param type the property type
     *
     * @throws NullPointerException if type is null
     * @throws IllegalArgumentException if type is not in PROPERTY_TYPE
     */
    private void validatePropertyType(final String type)
    {
        if(type == null)
        {
            throw  new NullPointerException("Invalid property type: null");
        }

        for(String propertyType : PROPERTY_TYPE)
        {
            if(propertyType.equalsIgnoreCase(type))
            {
                return;
            }
        }

        throw new IllegalArgumentException("Invalid property type: " +
                                            type);
    }


    /*
     * Validates the property ID.
     *
     * @param propertyId the property ID
     *
     * @throws NullPointerException     if propertyId is null
     * @throws IllegalArgumentException if propertyId is blank or longer than 6 characters
     */
    private void validatePropertyId(final String propertyId)
    {
        if(propertyId == null)
        {
            throw new NullPointerException("Invalid property id: null");
        }

        if(propertyId.isBlank() ||
           propertyId.length() > MAX_PROPERTY_ID_CHARACTERS)
        {
            throw new IllegalArgumentException("Invalid property id: " +
                    propertyId);
        }
    }
}
