package com.pocketedDays.persistence;

import com.pocketedDays.entity.Quote;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Test service client.
 */
public class TestServiceClient {

    /**
     * Test get pet by id success.
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetPetByIdSuccess() throws Exception {
        QuoteDao quoteDao = new QuoteDao();
        Quote quote = quoteDao.getById(1);
        Quote expectedQuote = new Quote();
        expectedQuote.setQuote("Life isn’t about getting and having, it’s about giving and being.");
        expectedQuote.setAuthor("Kevin Kruse");
        expectedQuote.setId(1);
        assertEquals(expectedQuote, quote);
    }
}