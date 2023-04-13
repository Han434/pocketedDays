package com.pocketedDays.utilities;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This interface contains a default method that can be used anywhere a
 * Properties object is needed to be loaded.
 *
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader {
    final Logger logger = Logger.getLogger(String.valueOf(PropertiesLoader.class));

    /**
     * This default method will load a properties file into a Properties
     * instance and return it.
     *
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance
     * if the file path was not found.
     * @throws Exception if an exception occurred
     */
    default Properties loadProperties(String propertiesFilePath)
            throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(
                    propertiesFilePath));
        } catch (IOException ioException) {
            logger.info("IO Exception occured.");
            throw ioException;
        } catch (Exception exception) {
            logger.info("Something is wrong.");
            throw exception;
        }
        return properties;
    }
}