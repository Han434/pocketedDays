package com.pocketedDays.utilities;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * The interface Number format interface.
 */
public interface NumberFormatInterface {

    /**
     * Gets formatted currency.
     *
     * @param numberToFormat the number to format
     * @return the formatted currency
     */
    default String getFormattedCurrency(int numberToFormat) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return numberFormat.format(numberToFormat);
    }

}

