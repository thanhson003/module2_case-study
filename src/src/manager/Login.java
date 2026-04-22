package manager;

import java.util.Scanner;

public class Login {
    private static Scanner scanner = new Scanner(System.in);
    private static String currentUser = "";

    public static boolean logIn() {
        System.out.println("--- ĐĂNG NHẬP HỆ THỐNG ---");
        System.out.print("Username: ");

        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        if (username.equals("admin") && password.equals("123456")) {
            currentUser = username;
            System.out.println("Đăng nhập thành công!");
            return true;
        }
        return false;
    }
}
