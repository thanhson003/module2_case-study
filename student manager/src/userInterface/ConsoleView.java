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
            System.out.println("\n========= QUẢN LÝ HỌC SINH =========");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Tìm kiếm sinh viên");
            System.out.println("3. Kiểm tra trạng thái học");
            System.out.println("4. Sửa thông tin sinh viên");
            System.out.println("5. Thay đổi trạng thái học");
            System.out.println("6. Xóa thông tin sinh viên");
            System.out.println("7. Hiển thị danh sách sinh viên");
            System.out.println("8. Đăng xuất");
            System.out.print("Chọn: ");
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
                    System.out.println("Đã đăng xuất thành công!");
                    isRunning = false;
                    break;
                case "0" : System.exit(0);
                default : System.out.println("Lựa chọn sai! Vui lòng lựa chọn lại");
            }
        }
    }
}
