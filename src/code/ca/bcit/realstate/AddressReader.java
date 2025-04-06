package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AddressReader class is used to read address data from a file
 * and convert it into a list of Address objects.
 * The file is expected to contain delimited address data.
 *
 * @author Noli Guerrero (A00871068)
 * @version 1.0
 */
public class AddressReader
{
    private static final int UNIT_NUMBER_INDEX   = 0;
    private static final int STREET_NUMBER_INDEX = 1;
    private static final int STREET_NAME_INDEX   = 2;
    private static final int POSTAL_CODE_INDEX   = 3;
    private static final int CITY_INDEX          = 4;

    private static final String REGEX_DELIMITER = "\\|";

    /**
     * Reads address data from the specified file and converts it into a list of Address objects.
     *
     * @param file the file containing the address data
     *
     * @return a list of Address objects
     *
     * @throws FileNotFoundException if the file cannot be found
     */
    public static ArrayList<Address> readAddressData(final File file)
                                            throws FileNotFoundException
    {
        final ArrayList<Address> addresses;
        final Scanner fileScanner;

        addresses   = new ArrayList<>();
        fileScanner = new Scanner(file);

        while(fileScanner.hasNext())
        {
            final String   line;
            final String[] rawData;
            final Address  address;
            final int      streetNumber;

            line    = fileScanner.nextLine();
            rawData = line.split(REGEX_DELIMITER);

            streetNumber = Integer.parseInt(rawData[STREET_NUMBER_INDEX]);

            address = new Address(rawData[UNIT_NUMBER_INDEX],
                                  streetNumber,
                                  rawData[STREET_NAME_INDEX],
                                  rawData[POSTAL_CODE_INDEX],
                                  rawData[CITY_INDEX]);
            addresses.add(address);

        }

        return addresses;
    }

}
