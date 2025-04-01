package ca.bcit.realstate;

public class Commercial extends Property
{
    private final boolean loadingDock;
    private final boolean highwayAccess;

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

    public boolean loadingDock()
    {
        return loadingDock;
    }

    public boolean highwayAccess()
    {
        return highwayAccess;
    }

    @Override
    public String toString()
    {
        final String parentoString;
        final String simpleClassName;

        parentoString = super.toString();
        simpleClassName = getClass().getSimpleName();

        return String.format("%s, %s{"           +
                             "loadingDock=%b, "  +
                             "highwayAccess=%b}",
                             parentoString,
                             simpleClassName,
                             loadingDock,
                             highwayAccess);
    }
}
