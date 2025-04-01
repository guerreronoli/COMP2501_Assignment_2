package ca.bcit.realstate;

public class Residence extends Property
{
    public static final int MIN_NUMBER_OF_BEDROOMS = 1;
    public static final int MAX_NUMBER_OF_BEDROOMS = 20;

    private final int     numberOfBedrooms;
    private final boolean swimmingPool;
    private final boolean strata;

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

    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    public boolean hasSwimmingPool()
    {
        return swimmingPool;
    }

    public boolean strata()
    {
        return strata;
    }

    @Override
    public String toString()
    {
        final String simpleClassName;
        final String parentToString;

        parentToString  = super.toString();
        simpleClassName = getClass().getSimpleName();

        return String.format("%s[" +
                             "numberofBedrooms=%d, " +
                             "swimmingPool=%b, "     +
                             "strata=%b, " +
                             "toString()=%s]",
                             simpleClassName,
                             numberOfBedrooms,
                             swimmingPool,
                             strata,
                             parentToString);

    }
}
