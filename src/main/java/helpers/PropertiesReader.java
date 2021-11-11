package helpers;

import utilities.PropertiesManager;

import java.util.Properties;

public class PropertiesReader {
    private final Properties prop;

    public PropertiesReader(String fileName) {
        prop = PropertiesManager.loadPropertyFile(Constants.MAIN_RESOURCES_PATH + fileName);
    }

    public String getProperty(String propName) {
        return prop.getProperty(propName);
    }
}
