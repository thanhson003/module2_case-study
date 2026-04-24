package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {
    // Regex cho ID: Ví dụ HV001 (2 chữ cái đầu, 3 số sau)
    public static final String ID_REGEX = "^[A-Z]{2}\\d{3}$";

    // Regex cho Tên: Tiếng Việt có dấu, không chứa số
    public static final String NAME_REGEX = "^[\\p{L} ]{2,50}$";

    // Regex cho Email: chuẩn quốc tế
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Regex cho SĐT: 10 số, bắt đầu bằng số 0
    public static final String PHONE_REGEX = "^0\\d{9}$";

    // Regex cho Ngày sinh: dd/mm/yyyy
    public static final String DATE_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    public static boolean validate(String input, String regex) {
        return Pattern.matches(regex, input);
    }

    public static boolean validateBirthDate(String dateStr) {
        // Bước 1: Kiểm tra định dạng cơ bản bằng Regex trước
        if (!validate(dateStr, DATE_REGEX)) {
            return false;
        }

        // Bước 2: Kiểm tra tính logic của thời gian thực
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate birthDate = LocalDate.parse(dateStr, formatter);
            LocalDate today = LocalDate.now();

            if (birthDate.isAfter(today)) {
                return false;
            }


            if (birthDate.isBefore(today.minusYears(100))) {
                return false;
            }
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
