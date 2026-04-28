package manager;

import model.Student;
import validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private static StudentManager manager;
    private static final Scanner sc = new Scanner(System.in);

    public StudentService(StudentManager manager) {
        StudentService.manager = manager;
    }

    private static boolean checkEmpty() {
        if (manager.getAll().isEmpty()) {
            System.out.println("⚠️ Danh sách sinh viên hiện đang trống! Vui lòng thêm dữ liệu trước.");
            return true;
        }
        return false;
    }

    private static String inputWithRegex(String message, String regex, String errorMsg) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (validate.Validator.validate(input, regex)) {
                return input;
            }
            System.err.println(errorMsg);
        }
    }

    public static void addStudent() {

        System.out.println("--- THÊM SINH VIÊN MỚI ---");

        String id;
        while (true) {
            id = inputWithRegex("Nhập ID (Ví dụ HV001): ", Validator.ID_REGEX,
                    "Lỗi: ID phải có dạng 2 chữ in hoa và 3 số!");

            if (manager.findById(id) != null) {
                System.err.println("⚠️ Lỗi: ID '" + id + "' đã tồn tại trong hệ thống! Vui lòng nhập một ID khác.");
            } else {
                break;
            }
        }

        String name = inputWithRegex("Nhập Tên: ",
                Validator.NAME_REGEX, "Lỗi: Tên không được chứa số hoặc ký tự đặc biệt!");

        String dob = inputWithRegex("Nhập Ngày sinh (dd/mm/yyyy): ",
                Validator.DATE_REGEX, "Lỗi: Ngày sinh phải đúng định dạng dd/mm/yyyy!");


        String gender;
        while (true) {
            System.out.print("Giới tính (Nam/Nữ): ");
            gender = sc.nextLine().trim();

            if (gender.equalsIgnoreCase("Nam") || gender.equalsIgnoreCase("Nữ")) {
                gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
                break;
            }
            System.err.println("⚠️ Lỗi: Giới tính chỉ được nhập 'Nam' hoặc 'Nữ'!");
        }

        String email = inputWithRegex("Nhập Email: ",
                Validator.EMAIL_REGEX, "Lỗi: Email không đúng định dạng!");

        System.out.print("Lớp: "); String className = sc.nextLine();

        System.out.print("Địa chỉ: "); String addr = sc.nextLine();

        String phoneStr = inputWithRegex("Nhập SĐT (10 số, bắt đầu bằng 0): ",
                Validator.PHONE_REGEX, "Lỗi: SĐT phải đủ 10 số và bắt đầu bằng 0!");
        String phone = sc.nextLine();

        if (manager.add(new Student(id, name, dob, gender, email, className,  true, addr, phone))) {
            System.out.println(">>> ✅ Thêm thành công! <<<");
        } else {
            System.out.println(">>> Thất bại: ID đã tồn tại! <<<");
        }
    }


    public static void searchStudent()  {
        if (checkEmpty()) return; // Kiểm tra danh sách trống

        System.out.println("\n--- TÌM KIẾM SINH VIÊN ---");
        System.out.println("1. Tìm kiếm theo ID");
        System.out.println("2. Tìm kiếm theo Tên");
        System.out.print("Chọn phương thức tìm kiếm (1 hoặc 2): ");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                System.out.print("Nhập ID sinh viên cần tìm: ");
                String id = sc.nextLine().trim();
                Student s = manager.findById(id);

                if (s != null) {
                    System.out.println(">>> ✅ Đã tìm thấy sinh viên:");
                    System.out.println(s);
                } else {
                    System.out.println("❌ Không tìm thấy sinh viên nào có ID là: " + id);
                }
                break;

            case "2":
                System.out.print("Nhập Tên sinh viên cần tìm: ");
                String name = sc.nextLine().trim();
                List<Student> result = manager.searchByName(name);

                if (result.isEmpty()) {
                    System.out.println("❌ Không tìm thấy sinh viên nào có tên : '" + name + "'");
                } else {
                    System.out.println(">>> ✅ Đã tìm thấy " + result.size() + " kết quả:");
                    result.forEach(System.out::println);
                }
                break;

            default:
                System.out.println("⚠️ Lựa chọn không hợp lệ! Vui lòng chỉ nhập 1 hoặc 2.");
                break;
        }
    }

    public static void checkStatus() {
        if (checkEmpty()) return;

        System.out.println("\n--- KIỂM TRA TRẠNG THÁI ---");
        System.out.print("Nhập ID sinh viên cần kiểm tra: ");
        String id = sc.nextLine().trim();

        Student hs = manager.findById(id);

        if (hs != null) {
            System.out.println("ID Sinh viên: " + hs.getId() + " | Tên: " + hs.getName() +
                    " | Trạng thái: " + (hs.isStatus() ? "Đang học" : "Đã nghỉ học/Tốt nghiệp"));
        } else {
            System.out.println("❌ Không tìm thấy sinh viên nào có ID là: " + id);
        }
    }

    public static void editStudent() {
        if (checkEmpty()) return;

        System.out.print("Nhập ID sinh viên cần sửa: ");
        String id = sc.nextLine().trim();
        Student s = manager.findById(id);

        if (s == null) {
            System.err.println("❌ Không tìm thấy sinh viên có ID: " + id);
            return;
        }

        System.out.println("--- Đang sửa sinh viên: " + s.getName() + " ---");
        System.out.println("(Nhấn Enter nếu muốn giữ nguyên thông tin cũ)");

        // 1. Sửa Tên
        System.out.print("Tên mới [" + s.getName() + "]: ");
        String newName = sc.nextLine().trim();
        if (!newName.isEmpty()) {
            if (validate.Validator.validate(newName, validate.Validator.NAME_REGEX)) {
                s.setName(newName);
            } else {
                System.err.println("⚠️ Tên sai định dạng, giữ lại tên cũ.");
            }
        }

        // 2. Sửa Giới tính
        System.out.print("Giới tính mới [" + s.getGender() + "]: ");
        String newGender = sc.nextLine().trim();
        if (!newGender.isEmpty()) {
            if (newGender.equalsIgnoreCase("Nam") || newGender.equalsIgnoreCase("Nữ")) {
                s.setGender(newGender.substring(0, 1).toUpperCase() + newGender.substring(1).toLowerCase());
            } else {
                System.err.println("⚠️ Chỉ nhận Nam/Nữ, giữ lại giới tính cũ.");
            }
        }

        // 3. Sửa Email
        System.out.print("Email mới [" + s.getEmail() + "]: ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty()) {
            if (validate.Validator.validate(newEmail, validate.Validator.EMAIL_REGEX)) {
                s.setEmail(newEmail);
            } else {
                System.err.println("⚠️ Email sai định dạng, giữ lại email cũ.");
            }
        }

        // 4. Sửa Lớp
        System.out.println("Lớp mới [" + s.getClassName() + "]: ");
        String newClass = sc.nextLine().trim();
        if (!newClass.isEmpty()) s.setClassName(newClass);

        // 5. Sửa Địa chỉ
        System.out.print("Địa chỉ mới [" + s.getAddress() + "]: ");
        String newAddr = sc.nextLine().trim();
        if (!newAddr.isEmpty()) s.setAddress(newAddr);

       //Sửa SDT
        System.out.print("Số điện thoại mới [" + s.getPhoneNumber() + "]: ");
        String newPhone = sc.nextLine().trim();
        if (!newPhone.isEmpty()) {
            if (validate.Validator.validate(newPhone, validate.Validator.PHONE_REGEX)) {
                s.setPhoneNumber(String.valueOf(Integer.parseInt(newPhone)));
            } else {
                System.err.println("⚠️ SĐT phải có 10 số, giữ lại SĐT cũ.");
            }
        }

        manager.saveChanges();
        System.out.println("✅ Đã cập nhật thông tin thành công!");
    }

    public static void changeStatus() {
        if(checkEmpty()){ return; }

        System.out.print("Nhập ID sinh viên cần thay đổi trạng thái: ");
        String id = sc.nextLine().trim();
        Student s = manager.findById(id);

        if (s == null) {
            System.err.println("❌ Không tìm thấy sinh viên có ID: " + id);
            return;
        }

        System.out.println("Chọn trạng thái mới:");
        System.out.println("1. Đang học");
        System.out.println("2. Bảo lưu");
        System.out.println("3. Đã nghỉ học");
        System.out.println("4. Tốt nghiệp");
        System.out.print("Lựa chọn của bạn: ");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                s.setStatus(true);
                System.out.println("✅ Đã cập nhật: Đang học");
                break;
            case "2":
                s.setStatus(false);
                System.out.println("✅ Đã cập nhật: Bảo lưu");
            case "3":
                s.setStatus(false);
                System.out.println("✅ Đã cập nhật: Đã nghỉ học");
                break;
            case "4":
                s.setStatus(false);
                System.out.println("✅ Đã cập nhật: Tốt nghiệp");
            case "":
                System.out.println("⚠️ Bạn không chọn gì, trạng thái được giữ nguyên.");
                return;
            default:
                System.err.println("❌ Lựa chọn không hợp lệ. Hủy bỏ thay đổi.");
                return;
        }

        manager.saveChanges();

    }

    public static void deleteStudent() {
        if (checkEmpty()) return;

        System.out.print("Nhập ID sinh viên muốn xóa: ");
        String id = sc.nextLine().trim();
        Student s = manager.findById(id);

        if (s != null) {
            System.out.println("⚠️ Bạn đang yêu cầu xóa sinh viên sau:");
            System.out.println(s);

            System.out.print("Bạn có chắc chắn muốn xóa không? (Nhập 'Y' để xóa, phím bất kỳ để hủy): ");
            String confirm = sc.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {
                if (manager.delete(id)) {
                    System.out.println("✅ Đã xóa sinh viên thành công!");
                } else {
                    System.err.println("❌ Có lỗi xảy ra, không thể xóa.");
                }
            } else {
                System.out.println("--- Đã hủy thao tác xóa ---");
            }
        } else {
            System.err.println("❌ Không tìm thấy sinh viên có ID: " + id);
        }
    }

    public static void displayAll() {
        if (checkEmpty()) return;
        manager.getAll().forEach(System.out::println);
    }
}