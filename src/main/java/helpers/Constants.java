package helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * A class contains the fixed constants and the loaded property files.
 * This class follow the Single Tone Pattern.
 */
public class Constants {
    public static final String TEST_RESOURCES_PATH = "src/test/resources/";
    public static final String MAIN_RESOURCES_PATH = "src/main/resources/";

    private static Constants instance = null;
    private final Map<String, PropertiesReader> propReaders;

    private Constants() {
        propReaders = new HashMap<>();
    }

    public static Constants getInstance() {
        if (instance == null)
            instance = new Constants();

        return instance;
    }

    /**
     * A private method to load a PropertiesReader instance in the PropReaders map
     * @param fileName the name of the property file
     */
    private void loadPropertiesFile(String fileName) {
            var propReader = new PropertiesReader(fileName);
            propReaders.put(fileName, propReader);
    }

    /**
     * Fetch the property form the file and check if this file is loaded before or not to don't load it again
     * @param fileName the name of the property file
     * @param propName the name of the property
     * @return the property value as String
     */
    public String getProperty(String fileName, String propName) {
        if (!propReaders.containsKey(fileName)) {
            loadPropertiesFile(fileName);
        }

        return propReaders
                .get(fileName)
                .getProperty(propName);
    }
}
