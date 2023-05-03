package com.pocketedDays.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocketedDays.entity.Quote;
import com.pocketedDays.utilities.PropertiesLoader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

/**
 * The type Quote dao.
 */
public class QuoteDao implements PropertiesLoader {
    private Properties properties;

    /**
     * Instantiates a new Quote dao.
     */
    public QuoteDao() {
        try {
            properties = loadProperties("/url.properties");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws JsonProcessingException the json processing exception
     */
    public Quote getById(int id) throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String url = properties.getProperty("id.url");
        url += id;
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = objectMapper.readValue(response, Quote.class);
        return quote;
    }

    /**
     * Gets random quote.
     *
     * @return the random quote
     * @throws JsonProcessingException the json processing exception
     */
    public Quote getRandomQuote() throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        String url = properties.getProperty("random.url");
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = objectMapper.readValue(response, Quote.class);
        return quote;
    }
}
