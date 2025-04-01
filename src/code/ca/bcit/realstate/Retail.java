package ca.bcit.realstate;

public class Retail extends Property
{
    private final int squareFootage;
    private final boolean customerParking;

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

    public int squareFootage()
    {
        return squareFootage;
    }

    public boolean customerParking()
    {
        return customerParking;
    }

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
                                     "toString()=%s]",
                             simpleClassName,
                             squareFootage,
                             customerParking,
                             parentToString);
    }
}
