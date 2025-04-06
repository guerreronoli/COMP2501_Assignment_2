package ca.bcit.realstate;

/**
 * Represents a commercial property with specific features such as
 * a loading dock and highway access.
 * Extends the Property class to include additional attributes
 * specific to commercial properties.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class Commercial extends Property
{
    private final boolean loadingDock;
    private final boolean highwayAccess;


    /**
     * Constructs a Commercial property with the specified details.
     *
     * @param priceUsd     the price of the property in USD
     * @param address      the address of the property
     * @param type         the type of the property
     * @param propertyId   the unique ID of the property
     * @param loadingDock  whether the property has a loading dock
     * @param highwayAccess whether the property has highway access
     */
    public Commercial(final double priceUsd,
                      final Address address,
                      final String type,
                      final String propertyId,
                      final boolean loadingDock,
                      final boolean highwayAccess)
    {
        super(priceUsd,
              address,
              type,
              propertyId);

        this.loadingDock   = loadingDock;
        this.highwayAccess = highwayAccess;
    }

    /**
     * Returns whether the property has a loading dock.
     *
     * @return true if the property has a loading dock, otherwise false
     */
    public final boolean hasLoadingDock()
    {
        return loadingDock;
    }

    /**
     * Returns whether the property has highway access.
     *
     * @return true if the property has highway access, otherwise false
     */
    public final boolean hasHighwayAccess()
    {
        return highwayAccess;
    }

    /**
     * Returns a string representation of the Commercial property.
     *
     * @return a formatted string containing the property details
     */
    @Override
    public String toString()
    {
        final String parentoString;
        final String className;

        parentoString   = super.toString();
        className = getClass().getSimpleName();

        return String.format("%s["           +
                             "loadingDock=%b, "  +
                             "highwayAccess=%b, " +
                             "%s]",
                             className,
                             loadingDock,
                             highwayAccess,
                             parentoString);
    }
}
