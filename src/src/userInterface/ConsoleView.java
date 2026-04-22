package userInterface;

import model.Student;
import manager.StudentManager;
import java.util.Scanner;

public class ConsoleView {
    private final StudentManager manager;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleView(StudentManager manager) {
        this.manager = manager;
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n========= MENU QUẢN LÝ HỌC SINH =========");
            System.out.println("1. Thêm HS");
            System.out.println("2. Tìm kiếm");
            System.out.println("3. Kiểm tra trạng thái");
            System.out.println("4. Sửa thông tin");
            System.out.println("5. Thay đổi trạng thái học");
            System.out.println("6. Thông tin tài khoản");
            System.out.println("7. Đăng xuất");
            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" : inputAdd();break;
                case "2" : inputSearch();break;
                case "3" : inputEdit();break;
                case "4" : inputToggleStatus();break;
                case "5" : displayAll();break;
                case "6" : displayAll();break;
                case "7" :
                    System.out.println("Đã đăng xuất thành công!");
                    isRunning = false;
                    break;
                case "0" : System.exit(0);
                default : System.out.println("Lựa chọn sai!");
            }
        }
    }

    private void inputAdd() {
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Tên: "); String name = sc.nextLine();
        System.out.print("Ngày sinh: "); String dob = sc.nextLine();
        System.out.print("Giới tính: "); String gender = sc.nextLine();
        System.out.print("Lớp: "); String className = sc.nextLine();
        System.out.print("GPA: "); double gpa = Double.parseDouble(sc.nextLine());
        System.out.print("Địa chỉ: "); String addr = sc.nextLine();
        System.out.print("SĐT: "); String phone = sc.nextLine();

        if (manager.add(new Student(id, name, dob, gender, className, gpa, true, addr, phone)))
            System.out.println("Thành công!");
        else System.out.println("ID đã tồn tại!");
    }

    private void inputSearch() {
        System.out.print("Nhập tên: ");
        manager.searchByName(sc.nextLine()).forEach(System.out::println);
        displayAll();
    }

    private void inputEdit() {
        System.out.print("Nhập ID: ");
        Student s = manager.findById(sc.nextLine());
        if (s != null) {
            System.out.print("Tên mới (" + s.getName() + "): "); s.setName(sc.nextLine());
            System.out.print("Lớp mới (" + s.getClassName() + "): "); s.setClassName(sc.nextLine());
            manager.saveChanges();
            System.out.println("Đã cập nhật!");
        } else System.out.println("Không thấy ID!");
    }

    private void inputToggleStatus() {
        System.out.print("Nhập ID: ");
        Student s = manager.findById(sc.nextLine());
        if (s != null) {
            s.setStatus(!s.isStatus());
            manager.saveChanges();
            System.out.println("Trạng thái mới: " + (s.isStatus() ? "Đang học" : "Đã nghỉ"));
        }
    }

    private void displayAll() {
        manager.getAll().forEach(System.out::println);
    }


}
