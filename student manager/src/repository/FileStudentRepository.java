package repository;

import model.Student;
import java.io.*;
import java.util.*;

public class FileStudentRepository implements StudentRepository {
    private final String fileName = "D:\\codegym\\Module_2\\Case Study\\student manager\\src\\file\\students.txt";

    @Override
    public  Map<String, Student> loadAll() {
        Map<String, Student> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 9) {
                    try {
                        Student s = new Student(
                                p[0],                              // id
                                p[1],                              // name
                                p[2],                              // birthDate
                                p[3],                              // gender
                                p[4],                              // email
                                p[5],                              // className
                                Boolean.parseBoolean(p[6].trim()), // status
                                p[7],                              // address
                                p[8]                               // phone
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
                pw.println(String.format("%s,%s,%s,%s,%s,%s,%b,%s,%s",
                        s.getId(), s.getName(), s.getBirthDate(), s.getGender(),
                        s.getEmail(), s.getClassName(), s.isStatus(),
                        s.getAddress(), s.getPhoneNumber()));
            }
        } catch (IOException e) {
            System.out.println("Lỗi lưu file: " + e.getMessage());
        }
    }
}