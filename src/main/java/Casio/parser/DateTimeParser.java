package Casio.parser;
import Casio.exceptions.CasioException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;


public class DateTimeParser {
    private static final DateTimeFormatter[] FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"),
            DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/MM/d HHmm"),
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-dd HHmm"),
            DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/M/dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/dd HHmm"),
            DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy/M/d HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
    };

    public static LocalDateTime parseDateTime(String dateTimeStr) throws CasioException {
        for (DateTimeFormatter formatter : FORMATS) {
            try {
                return LocalDateTime.parse(dateTimeStr, formatter);
            } catch (DateTimeParseException ignored) {

            }
        }
        CasioException.unrecognizedDateTime();
        return null;
    }

    public static String dateTimeToString(LocalDateTime dateTime) throws CasioException {
        try {
            DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
            return dateTime.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            CasioException.unrecognizedDateTime();
            return null;
        }
    }

    public static String saveDateTimeFormat(LocalDateTime dateTime) throws CasioException {
        try {
            DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return dateTime.format(OUTPUT_FORMAT);
        } catch (DateTimeParseException e) {
            CasioException.unrecognizedDateTime();
            return null;
        }
    }



}
