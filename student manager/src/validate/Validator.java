package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {
    // Regex cho ID: Ví dụ HV001 (2 chữ cái đầu, 3 số sau)
    public static final String ID_REGEX = "^[A-Z]{2}\\d{3}$";

    // Regex cho Tên: Tiếng Việt có dấu, không chứa số
    public static final String NAME_REGEX = "^[\\p{L} ]{1,50}$";

    // Regex cho Email: chuẩn quốc tế
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Regex cho SĐT: 10 số, bắt đầu bằng số 0
    public static final String PHONE_REGEX = "^0\\d{9}$";

    // Regex cho Ngày sinh: dd/mm/yyyy
    public static final String DATE_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    public static boolean validate(String input, String regex) {
        return Pattern.matches(regex, input);
    }
}
