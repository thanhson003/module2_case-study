package repository;

import model.Student;
import java.io.*;
import java.util.*;

public class FileStudentRepository implements StudentRepository {
    private final String fileName = "students.txt";

    @Override
    public Map<String, Student> loadAll() {
        Map<String, Student> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 10) {
                    Student s = new Student(p[0], p[1], p[2], p[3], p[4],p[5],
                            Double.parseDouble(p[7]), Boolean.parseBoolean(p[8]),
                             p[9], p[10]);
                    map.put(s.getId().toUpperCase(), s);
                }
            }
        } catch (IOException ignored) {}
        return map;
    }

    @Override
    public void saveAll(Map<String, Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students.values()) {
                // Thêm s.getEmail() vào cuối dòng
                pw.println(String.format("%s,%s,%s,%s,%s,%f,%s,%s,%d,%s",
                        s.getId(), s.getName(), s.getBirthDate(), s.getGender(),
                        s.getClassName(), s.getGpa(), s.isStatus(),
                        s.getAddress(), s.getPhoneNumber(), s.getEmail()));
            }
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }
}