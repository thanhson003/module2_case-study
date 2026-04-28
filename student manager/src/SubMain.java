import manager.StudentManager;
import manager.StudentService;
import repository.FileStudentRepository;
import repository.StudentRepository;
import userInterface.ConsoleView;

import java.util.Scanner;

import static manager.Login.logIn;

public class SubMain {
    public static void run() {
        if (!logIn()) {
            System.out.println("Đăng nhập thất bại !");
            return;
        }

        StudentRepository repo = new FileStudentRepository();
        StudentManager service = new StudentManager(repo);
        new StudentService(service);
        ConsoleView view = new ConsoleView(service);
        view.start();
    }
}
