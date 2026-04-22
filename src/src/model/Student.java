package model;

public class Student extends Person {
    private String id;
    private String email;
    private String className;
    private double gpa;
    private boolean status;
    private String address;
    private Integer phoneNumber;

    public Student(String id, String name, String birthDate, String gender, String email,
                   String className, double gpa, boolean status, String address, Integer phoneNumber) {
        super();
        this.id = id;
        this.email = email;
        this.className = className;
        this.gpa = gpa;
        this.status = status;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Student(String id, String name, String dob, String gender, String className, double gpa, boolean b, String addr, String phone) {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        String statusText = status ? "Đang học" : "Đã nghỉ";
        return String.format("ID: %-5s | %s | Lớp: %-7s | GPA: %-4.1f | TT: %-10s | Email: %-20s | ĐC: %-10s | SĐT: %-10d",
                id, super.toString(), className, gpa, statusText, email, address, phoneNumber);
    }
}