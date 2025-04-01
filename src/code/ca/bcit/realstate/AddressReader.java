package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressReader
{
    public static final int UNIT_NUMBER_INDEX   = 0;
    public static final int STREET_NUMBER_INDEX = 1;
    public static final int STREET_NAME_INDEX   = 2;
    public static final int POSTAL_CODE_INDEX   = 3;
    public static final int CITY_INDEX          = 4;

    public static final String REGEX_DELIMITER = "\\|";

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
