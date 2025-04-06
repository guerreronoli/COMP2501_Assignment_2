package ca.bcit.realstate;

/**
 * Represents a retail property with specific attributes such as
 * square footage and customer parking availability.
 * Extends the Property class to include features unique to retail properties.
 *
 * @author Noli Guerrero
 * @version 1.0
 */
public class Retail extends Property
{
    private final int squareFootage;
    private final boolean customerParking;

    /**
     * Constructs a Retail object with the specified details.
     *
     * @param priceUsd        the price of the property in USD
     * @param address         the address of the property
     * @param type            the type of the property
     * @param propertyId      the unique identifier for the property
     * @param squareFootage   the total square footage of the retail property
     * @param customerParking whether the property includes customer parking
     */
    public Retail(final double priceUsd,
                  final Address address,
                  final String type,
                  final String propertyId,
                  final int squareFootage,
                  final boolean customerParking)
    {
        super(priceUsd,
              address,
              type,
              propertyId);

        this.squareFootage   = squareFootage;
        this.customerParking = customerParking;
    }

    /**
     * Returns the square footage of the retail property.
     *
     * @return the square footage of the property
     */
    public int getSquareFootage()
    {
        return squareFootage;
    }

    /**
     * Indicates whether the retail property has customer parking.
     *
     * @return true if the property has customer parking, otherwise false
     */
    public boolean hasCustomerParking()
    {
        return customerParking;
    }

    /**
     * Returns a string representation of the Retail property.
     * Includes the class name, square footage, customer parking status,
     * and details from the parent class.
     *
     * @return a formatted string containing the retail property details
     */
    @Override
    public String toString()
    {
        final String parentToString;
        final String simpleClassName;

        parentToString = super.toString();
        simpleClassName = getClass().getSimpleName();

        return String.format("%s["            +
                             "squareFootage=%d, " +
                             "customerParking=%b, " +
                             "%s]",
                             simpleClassName,
                             squareFootage,
                             customerParking,
                             parentToString);
    }
}
