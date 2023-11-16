package Controller;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 The studentControllerInterface provides an interface for the controller in the MVC pattern for the Student Management System.
 */
public interface studentControllerInterface {
    /**
     * Adds student information to the database.
     *
     * @param NameField the TextField for the student's name
     * @param IdField the TextField for the student's ID
     * @param dobField the DatePicker for the student's date of birth
     * @param currentSemester the TextField for the student's current semester
     * @param tab1TextField the TextField to display success/error messages
     */
    void addInfo(TextField NameField, TextField IdField, DatePicker dobField, TextField currentSemester, TextField tab1TextField);

    /**
     * Remove students from the database.
     *
     * @param IdField the TextField for the student's ID
     * @param tab1TextField the TextField to display success/error messages
     */
    void removeInfo(TextField IdField, TextField tab1TextField);

    /**
     * Show the list of students in the database.
     *
     * @param textArea the TextArea to display the list of students
     * @param tab1TextField the TextField to display success/error messages
     */
    void listStudent(TextArea textArea, TextField tab1TextField);

    /**
     * This method simulate the memory leak unit the heap space run out of memory
     */
    void simulateMemoryLeak();

    /**
     * Add module information to the database.
     *
     * @param moduleField the TextField for the module's name
     * @param gradeField the TextField for the module's grade
     * @param semesterField the TextField for the module's semester
     * @param tab2textField the TextField to display success/error messages
     */
    void addModuleInfo(TextField moduleField, TextField gradeField, TextField semesterField, TextField tab2textField);

    /**
     * Remove module information from the database.
     *
     * @param moduleCode the TextField for the module's code
     * @param tab2textField the TextField to display success/error messages
     */
    void removeModuleInfo(TextField moduleCode, TextField tab2textField);

    /**
     * Set the search ComboBox with student IDs and names from the student database.
     */
    void setSearchComboBox();

    /**
     * Search for a student in the database and display their information in a TextArea.
     *
     * @param tab1textField the TextField to display success/error messages
     * @param textArea the TextArea to display the student's information
     */
    void searchStudent(TextField tab1textField, TextArea textArea);

    /**
     * Set the student ComboBox with student IDs and names from the student database.
     */
    void setStudentComboBox();

    /**
     * Set the modules ComboBox with Module name and Module code from the student database.
     */
    void setModulesComboBox();

    /**
     * Add a grade for a module to the database.
     *
     * @param modulesGradeField the TextField for the module's grade
     * @param tab3textField the TextField to display success/error messages
     * @throws SQLException if there was a problem with the database connection or query
     */
    void addGrade(TextField modulesGradeField, TextField tab3textField) throws SQLException;

    /**
     * Modify the grade of a module in the database.
     *
     * @param modulesGradeField the TextField for the module's new grade
     * @param tab3textField the TextField to display success/error messages
     */
    void modifyGrade(TextField modulesGradeField, TextField tab3textField);

    /**
     * Remove the grade of a module from the database.
     *
     * @param tab3textField the TextField to display success/error messages
     */
    void removeGrade(TextField tab3textField);

}
