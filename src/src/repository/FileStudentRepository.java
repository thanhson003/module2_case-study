package repository;

import model.Student;
import java.io.*;
import java.util.*;

public class FileStudentRepository implements StudentRepository {
    // Bạn nên kiểm tra lại đường dẫn này xem có đúng trong máy bạn không
    private final String fileName = "D:\\codegym\\Module_2\\Case Study\\src\\src\\file\\students.txt";

    @Override
    public Map<String, Student> loadAll() {
        Map<String, Student> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 10) {
                    try {
                        Student s = new Student(
                                p[0], // id
                                p[1], // name
                                p[2], // birthDate
                                p[3], // gender
                                p[4], // email
                                p[5], // className
                                Double.parseDouble(p[6].trim()),   // GPA
                                Boolean.parseBoolean(p[7].trim()), // status
                                p[8], // address
                                Integer.parseInt(p[9].trim())      // phone
                        );
                        map.put(s.getId().toUpperCase(), s);
                    } catch (Exception e) {
                        System.out.println("Bỏ qua 1 dòng lỗi: " + e.getMessage());
                    }
                }
            }
        } catch (IOException ignored) {}
        return map;
    }

    @Override
    public void saveAll(Map<String, Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students.values()) {
                pw.println(String.format("%s,%s,%s,%s,%s,%s,%.2f,%b,%s,%d",
                        s.getId(), s.getName(), s.getBirthDate(), s.getGender(),
                        s.getEmail(), s.getClassName(), s.getGpa(), s.isStatus(),
                        s.getAddress(), s.getPhoneNumber()));
            }
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }
}