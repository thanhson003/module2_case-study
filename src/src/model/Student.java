package model;

public class Student extends Person {
    private String id;
    private String email;
    private String className;

    private boolean status;
    private String address;
    private Integer phoneNumber;

    public Student(String id, String name, String birthDate, String gender, String email,
                   String className,  boolean status, String address, Integer phoneNumber) {
        super(name, birthDate, gender);
        this.id = id;
        this.email = email;
        this.className = className;

        this.status = status;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
        return String.format("ID: %-5s | %s | Lớp: %-7s | TT: %-10s | Email: %-20s | ĐC: %-10s | SĐT: %-10d",
                id, super.toString(), className, statusText, email, address, phoneNumber);
    }
}