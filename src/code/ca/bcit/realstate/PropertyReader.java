package ca.bcit.realstate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PropertyReader
{

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
