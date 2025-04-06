package ca.bcit.realstate;

/**
 * Represents a residential property with additional attributes such as
 * the number of bedrooms, swimming pool availability, and strata status.
 * Extends the Property class to add residential-specific features.
 *
 * @author Noli Guerrero
 * @version 1.0
 */
public class Residence extends Property
{
    /**
     * The minimum number of bedrooms allowed in a residence.
     */
    public static final int MIN_NUMBER_OF_BEDROOMS = 1;

    /**
     * The maximum number of bedrooms allowed in a residence.
     */
    public static final int MAX_NUMBER_OF_BEDROOMS = 20;

    private final int     numberOfBedrooms;
    private final boolean swimmingPool;
    private final boolean strata;

    /**
     * Constructs a Residence object with the specified details.
     *
     * @param priceUsd        the price of the property in USD
     * @param address         the address of the property
     * @param numberOfBedrooms the number of bedrooms in the residence
     * @param swimmingPool    whether the residence has a swimming pool
     * @param type            the type of the property
     * @param propertyId      the unique identifier for the property
     * @param strata          whether the residence is part of a strata
     *
     * @throws IllegalArgumentException if the number of bedrooms is out of the valid range
     */
    public Residence(final double  priceUsd,
                     final Address address,
                     final int     numberOfBedrooms,
                     final boolean swimmingPool,
                     final String  type,
                     final String  propertyId,
                     final boolean strata)
    {
        super(priceUsd,
              address,
              type,
              propertyId);

        if(numberOfBedrooms < MIN_NUMBER_OF_BEDROOMS ||
           numberOfBedrooms > MAX_NUMBER_OF_BEDROOMS)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " +
                                                       numberOfBedrooms);
        }

        this.numberOfBedrooms = numberOfBedrooms;
        this.swimmingPool     = swimmingPool;
        this.strata           = strata;
    }

    /**
     * Returns the number of bedrooms in the residence.
     *
     * @return the number of bedrooms
     */
    public final int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    /**
     * Indicates whether the residence has a swimming pool.
     *
     * @return true if the residence has a swimming pool, otherwise false
     */
    public final boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    /**
     * Indicates whether the residence is part of a strata.
     *
     * @return true if the residence is part of a strata, otherwise false
     */
    public final boolean hasStrata()
    {
        return strata;
    }

    /**
     * Returns a string representation of the Residence object.
     * Includes the class name, number of bedrooms, swimming pool status, strata status,
     * and details from the parent class.
     *
     * @return a formatted string representation of the residence
     */
    @Override
    public String toString()
    {
        final String simpleClassName;
        final String parentToString;

        parentToString  = super.toString();
        simpleClassName = getClass().getSimpleName();

        return String.format("%s[" +
                             "numberOfBedrooms=%d, " +
                             "swimmingPool=%b, "     +
                             "strata=%b, " +
                             "%s]",
                             simpleClassName,
                             numberOfBedrooms,
                             swimmingPool,
                             strata,
                             parentToString);

    }
}
