package com.dk.umwerktestapp.utils.format;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by David on 22-Apr-17.
 */

public class DateFormatter {

    public static String toFriendlyLongDateTimeString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        final ZonedDateTime parsed = ZonedDateTime.parse(date, formatter.withZone(ZoneId.of("UTC")));

        return parsed.toLocalDate().toString();
    }
}
