package Model;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 The Student class represents a student in the system for the simulation.
 */
public class Student {
    private String name;
    private String studentId;
    private DatePicker dob;
    private String currentSemester;
    private String[] modules;

    /**
     * Constructor for the Student class.
     * @param name The name of the student.
     * @param studentId The ID of the student.
     * @param dob The date of birth of the student.
     * @param currentSemester The current semester of the student.
     */
    public Student(String name, String studentId, DatePicker dob, String currentSemester) {
        this.name = name;
        this.studentId = studentId;
        this.dob = dob;
        this.currentSemester = currentSemester;
    }

    // Getters and setters for the attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public DatePicker getDob() {
        return dob;
    }

    public void setDob(DatePicker dob) {
        this.dob = dob;
    }

    public String getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(String currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String[] getModules() {
        return modules;
    }

    public void setModules(String[] modules) {
        this.modules = modules;
    }

    public String toString() {
        return "name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", dob=" + dob.getValue() +
                ", currentSemester='" + currentSemester;
    }
}
