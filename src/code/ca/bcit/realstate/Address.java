package ca.bcit.realstate;

/**
 * Represents an address with a unit number, street number, street name,
 * postal code, and city. Includes validation to ensure the integrity of the data.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class Address
{
    public static final int MAX_UNIT_NUMBER_CHARACTERS = 4;

    public static final int STREET_NUMBER_START = 0;
    public static final int STREET_NUMBER_END   = 999999;

    public static final int MIN_STREET_NAME_CHARACTERS = 1;
    public static final int MAX_STREET_NAME_CHARACTERS = 20;

    public static final int MIN_POSTAL_CODE_CHARACTERS = 5;
    public static final int MAX_POSTAL_CODE_CHARACTERS = 6;

    public static final int MIN_CITY_CHARACTERS = 1;
    public static final int MAX_CITY_CHARACTERS = 30;

    private final String unitNumber;
    private final int    streetNumber;
    private final String streetName;
    private final String postalCode;
    private final String city;


    /**
     * Constructs an Address object with the specified details.
     *
     * @param unitNumber   the unit number (optional, must be 1-4 characters if provided)
     * @param streetNumber the street number (must be between 0 and 999999)
     * @param streetName   the street name (must be between 1 and 20 characters)
     * @param postalCode   the postal code (must be 5 or 6 characters)
     * @param city         the city name (must not be blank and up to 30 characters)
     *
     * @throws IllegalArgumentException if any parameter is invalid
     * @throws NullPointerException     if streetName, postalCode, or city is null
     */
    public Address(final String unitNumber,
                   final int    streetNumber,
                   final String streetName,
                   final String postalCode,
                   final String city)
    {


        if(unitNumber != null)
        {
            if(unitNumber.isEmpty() ||
               unitNumber.length() > MAX_UNIT_NUMBER_CHARACTERS)
            {
                throw new IllegalArgumentException("Invalid unit number: " +
                                                    unitNumber);
            }
        }

        if(streetNumber < STREET_NUMBER_START ||
           streetNumber > STREET_NUMBER_END)
        {
            throw new IllegalArgumentException("Invalid street number: " +
                                                streetNumber);
        }


        if(streetName == null)
        {
            throw new NullPointerException("Invalid street name: null");
        }
        else
        {
            if(streetName.isBlank() ||
               streetName.length() > MAX_STREET_NAME_CHARACTERS)
            {
                throw new IllegalArgumentException("Invalid street name: " +
                                                    streetName);
            }
        }

        if(postalCode == null)
        {
            throw new NullPointerException("Invalid postal code: null");
        }
        else
        {
            if(postalCode.length() < MIN_POSTAL_CODE_CHARACTERS ||
               postalCode.length() > MAX_POSTAL_CODE_CHARACTERS)
            {
                throw new IllegalArgumentException("Invalid postal code: " +
                        postalCode);
            }
        }

        if(city == null)
        {
            throw new NullPointerException("Invalid city: null");
        } else
        {
            if(city.isBlank() ||
               city.length() > MAX_CITY_CHARACTERS)
            {
                throw new IllegalArgumentException("Invalid city: " +
                                                    city);
            }
        }

        this.unitNumber   = unitNumber;
        this.streetNumber = streetNumber;
        this.streetName   = streetName;
        this.postalCode   = postalCode;
        this.city         = city;
    }

    @Override
    public String toString()
    {
        final String simpleClassName;
        simpleClassName = getClass().getSimpleName();

        return String.format("%s["              +
                             "unitNumber=%s, "   +
                             "streetNumber=%d, " +
                             "streetName=%s, "   +
                             "postalCode=%s, "   +
                             "city=%s]",
                             simpleClassName,
                             unitNumber,
                             streetNumber,
                             streetName,
                             postalCode,
                             city);
    }

    /**
     * Returns the unit number.
     *
     * @return the unit number (maybe null)
     */
    public String getUnitNumber()
    {
        return unitNumber;
    }

    /**
     * Returns the street number.
     *
     * @return the street number
     */
    public int getStreetNumber()
    {
        return streetNumber;
    }

    /**
     * Returns the street name.
     *
     * @return the street name
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * Returns the postal code.
     *
     * @return the postal code
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Returns the city name.
     *
     * @return the city name
     */
    public String getCity()
    {
        return city;
    }
}
