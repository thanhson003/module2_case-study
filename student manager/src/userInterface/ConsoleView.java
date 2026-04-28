package userInterface;

import manager.StudentManager;
import java.util.Scanner;

import static manager.StudentService.*;


public class ConsoleView {
    private final StudentManager manager;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleView(StudentManager manager) {
        this.manager = manager;
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│             🎓 HỆ THỐNG QUẢN LÝ SINH VIÊN              │");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.println("│  1. ➕ Thêm sinh viên         5. 🔄 Đổi trạng thái     │");
            System.out.println("│  2. 🔍 Tìm kiếm               6. ❌ Xóa sinh viên      │");
            System.out.println("│  3. 📝 Sửa thông tin          7. 📋 Hiện danh sách     │");
            System.out.println("│  4. 🔔 Kiểm tra trạng thái    8. 🚪 Đăng xuất          │");
            System.out.println("└────────────────────────────────────────────────────────┘");
            System.out.print("\uD83D\uDC49 Lựa chọn của bạn:");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" : addStudent(); break;
                case "2" : searchStudent(); break;
                case "3" : checkStatus(); break;
                case "4" : editStudent(); break;
                case "5" : changeStatus(); break;
                case "6" : deleteStudent(); break;
                case "7" : displayAll(); break;
                case "8" :
                    System.out.println("\uD83D\uDC4B Đã đăng xuất thành công!");
                    isRunning = false;
                    break;
                case "0" : System.exit(0);
                default : System.out.println("❌ Lựa chọn sai! Vui lòng lựa chọn lại");
            }
        }
    }
}
