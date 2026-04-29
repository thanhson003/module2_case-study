package manager;

import model.Student;
import repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StudentManager {
        private final StudentRepository repo;
        private final Map<String, Student> students;

        public StudentManager(StudentRepository repo) {
                this.repo = repo;
                this.students = repo.loadAll();
        }

        public boolean add(Student hs) {
                if (students.containsKey(hs.getId().toUpperCase())) return false;
                students.put(hs.getId().toUpperCase(), hs);
                repo.saveAll(students);
                return true;
        }

        public List<Student> searchByName(String name) {
                return students.values().stream()
                        .filter(hs -> hs.getName().toLowerCase().contains(name.toLowerCase()))
                        .toList();
        }

        public Student findById(String id) {
                return students.get(id.toUpperCase());
        }

        public void saveChanges() {
                repo.saveAll(students);
        }

        public Collection<Student> getAll() {
                return students.values();
        }

        public boolean delete(String id) {
                if (students.containsKey(id.toUpperCase())) {
                        students.remove(id.toUpperCase());
                        repo.saveAll(students);
                        return true;
                }
                return false;
        }
}