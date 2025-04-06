package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The PropertyReader class is responsible for reading property data from a file
 * and returning it as a list of strings.
 * Each line in the file represents a property and is added to the list.
 * This class provides utility for processing raw property data.
 *
 * @author Noli Guerrero
 * @version 1.0
 */
public class PropertyReader
{

    /**
     * Reads property data from the specified file and returns it as a list of strings.
     *
     * @param file the file containing property data
     *
     * @return a list of strings, where each string represents a line of property data
     *
     * @throws FileNotFoundException if the specified file cannot be found
     */
    public static ArrayList<String> readPropertyData(final File file) throws FileNotFoundException
    {
        final ArrayList<String> properties;
        final Scanner fileScanner;

        properties = new ArrayList<>();
        fileScanner = new Scanner(file);

        while(fileScanner.hasNext())
        {
            final String line;
            line = fileScanner.nextLine();

            properties.add(line);
        }

        return properties;
    }
}
