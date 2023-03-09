package com.pocketedDays.utilities;

import java.text.NumberFormat;
import java.util.Locale;

public interface NumberFormatInterface {

    default String getFormattedCurrency(int numberToFormat) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return numberFormat.format(numberToFormat);
    }

}

