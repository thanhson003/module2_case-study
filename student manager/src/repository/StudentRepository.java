package repository;

import model.Student;
import java.util.Map;

public interface StudentRepository {
    Map<String, Student> loadAll();
    void saveAll(Map<String, Student> danhSach);
}
