package gobear.automation.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

    public static LocalDate stringToDate(String input) {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
