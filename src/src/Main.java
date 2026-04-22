import repository.FileStudentRepository;
import repository.StudentRepository;
import manager.StudentManager;
import userInterface.ConsoleView;
import static manager.Login.logIn;

public class Main {
    public static void main(String[] args) {
        if (!logIn()) {
            System.out.println("Đăng nhập thất bại !");
            return;
        }

        StudentRepository repo = new FileStudentRepository();
        StudentManager service = new StudentManager(repo);
        ConsoleView view = new ConsoleView(service);

        view.start();
    }
}