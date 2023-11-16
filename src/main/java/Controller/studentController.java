package Controller;

import Model.Module;
import Model.Student;
import View.studentView;
import Model.studentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 The studentController class implements the studentControllerInterface and serves as the controller in the MVC pattern for the Student Management.
 */
public class studentController implements studentControllerInterface{
    public studentModel model;
    public studentView view;


    /**
     Constructor for the student controller.
     @param view the student view
     */
    public studentController(studentView view){
        this.model = new studentModel();
        model.connect();

        this.view = view;
    }

    /**
     Adds student information to the database.
     @param NameField the TextField for the student's name
     @param IdField the TextField for the student's ID
     @param dobField the TextField for the student's date of birth
     @param currentSemester the TextField for the student's current semester
     @param tab1TextField the TextField to display success/error messages
     */
    public void addInfo(TextField NameField, TextField IdField, DatePicker dobField, TextField currentSemester, TextField tab1TextField) {
        // Check if any of the fields is empty
        if (NameField.getText().isEmpty() || IdField.getText().isEmpty() || dobField.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Information Not Added");
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
            return;
        }

        // Create a connection to the database
        try{
            // Prepare a statement to insert the student data
            PreparedStatement stmt = model.getConnection().prepareStatement("INSERT INTO record.student (name, student_id, dob, currentSemester) VALUES (?, ?, ?, ?)");
            stmt.setString(1, NameField.getText());
            stmt.setString(2, IdField.getText());
            stmt.setDate(3, java.sql.Date.valueOf(dobField.getValue()));
            stmt.setString(4, currentSemester.getText());

            // Execute the statement to insert the data
            stmt.executeUpdate();

            // Update the text area to indicate success
            tab1TextField.setText("Data is successfully added to the Student database");
            setSearchComboBox();
            setStudentComboBox();
        } catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * remove students from the database
     * @param IdField the TextField for the student's ID
     * @param tab1TextField the TextField to display success/error messages
     */
    public void removeInfo(TextField IdField, TextField tab1TextField){
        // Check if the student ID field is empty
        if (IdField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("ID Not Entered");
            alert.setContentText("Please enter the ID of the student to remove");
            alert.showAndWait();
            return;
        }

        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("DELETE FROM record.student WHERE student_ID = ?");
            stmt.setString(1, IdField.getText());

            int studentRowsDeleted = stmt.executeUpdate();

            if (studentRowsDeleted > 0) {
                tab1TextField.setText("Data for student ID " + IdField.getText() + " is successfully removed from the Student database");
                setSearchComboBox();
                setStudentComboBox();
            } else {
                tab1TextField.setText("No data found for student ID " + IdField.getText());
            }
        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * Show the list of students in the database
     * @param textArea the TextArea to display the list of students
     * @param tab1TextField the TextField to display success/error messages
     */
    public void listStudent(TextArea textArea, TextField tab1TextField){
        try{
            // Prepare a statement to insert the student data
            PreparedStatement stmt = model.getConnection().prepareStatement("SELECT * FROM record.student ORDER BY name");
            // Execute the statement to insert the data
            ResultSet rs = stmt.executeQuery();
            textArea.clear();
            // Add heading for the data displayed on the screen
            textArea.appendText(String.format("%-15s %-15s %-15s %-10s\n", "Student ID", "Name", "DOB", "Semester"));

            while (rs.next()) {
                String studentID = rs.getString("student_ID");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                int semester = rs.getInt("currentSemester");

                textArea.appendText(String.format("%-15s %-15s %-15s %-10d\n", studentID, name, dob, semester));
            }

            // Close the result set and statement
            rs.close();
            stmt.close();

            tab1TextField.setText("Student record: ");
        } catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * This method simulate the memory leak unit the heap space run out of memory
     */
    public void simulateMemoryLeak() {
        List<Student> students = new ArrayList<>();
        int index = 1;
        Runtime rt = Runtime.getRuntime();

        try {
            while (true) {
                Student s = new Student("Student" + index, String.valueOf(index), new DatePicker(), "Semester" + index);
                students.add(s);
                System.out.printf("[%d] free memory: %d%n", index++, rt.freeMemory());
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory at index " + (index - 1));
            System.out.printf("Total memory used: %d bytes%n", rt.totalMemory() - rt.freeMemory());
        }
    }


    /**
     * Add module
     * @param moduleName the TextField for the module's name
     * @param moduleCode the TextField for the module's grade
     * @param semesterField the TextField for the module's semester
     * @param tab2textField the TextField to display success/error messages
     */
    public void addModuleInfo(TextField moduleName, TextField moduleCode, TextField semesterField, TextField tab2textField){
        if (moduleName.getText().isEmpty() || moduleCode.getText().isEmpty() || semesterField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Information Not Added");
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
            return;
        }
        Module module = new Module(moduleName.getText(),  moduleCode.getText(), semesterField.getText());
        module.setModuleCode(moduleName.getText());
        module.setModuleCode(moduleCode.getText());
        module.setSemester(semesterField.getText());

        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("INSERT INTO record.modules (moduleCode, moduleName, semester) VALUES (?, ?, ?)");
            stmt.setString(1, moduleName.getText());
            stmt.setString(2, moduleCode.getText());
            stmt.setString(3, semesterField.getText());

            // Execute the statement to insert the data
            stmt.executeUpdate();

            // Update the text area to indicate success
            tab2textField.setText("Data is successfully added to the Modules database");
            setModulesComboBox();
        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     Removes module information from the database.
     @param moduleCode the TextField for the module's code
     @param tab2textField the TextField to display success/error messages
     */
    public void removeModuleInfo(TextField moduleCode, TextField tab2textField){
        if (moduleCode.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("ID Not Entered");
            alert.setContentText("Please enter the ID of the student to remove");
            alert.showAndWait();
            return;
        }
        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("DELETE FROM record.modules WHERE moduleCode=?");
            stmt.setString(1, moduleCode.getText());

            int studentRowsDeleted = stmt.executeUpdate();

            if (studentRowsDeleted > 0) {
                tab2textField.setText("Data for module code " + moduleCode.getText() + " is successfully removed");
                setModulesComboBox();
            } else {
                tab2textField.setText("No data found for module code MATHS001");
            }
        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     Sets the search ComboBox with student IDs and names from the student database.
     */
    public void setSearchComboBox() {
        try {
            PreparedStatement stmt = model.getConnection().prepareStatement("SELECT student_ID, name FROM record.student");
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> studentList = FXCollections.observableArrayList();

            while (rs.next()) {
                String student_ID = rs.getString("student_ID");
                String name = rs.getString("name");
                String studentIdAndNames = student_ID + " - " + name;
                studentList.add(studentIdAndNames);
            }

            view.getSearchComboBox().setItems(studentList);
            //view.getStudentComboBox().setPromptText("Student List");
        } catch (SQLException e){
            System.out.println("Please select a Student first");
        }
    }

    /**
     Searches for a student in the database and displays their information in a TextArea.
     @param tab1textField the TextField to display success/error messages
     @param textArea the TextArea to display the student's information
     */
    public void searchStudent(TextField tab1textField, TextArea textArea){
        String selectedStudent = view.getSearchComboBox().getValue();

        String[] studentParts = selectedStudent.split(" - ");
        String selectedStudentID = studentParts[0];

        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("SELECT m.moduleCode, m.moduleName, g.grade FROM record.student AS s JOIN record.grades AS g ON s.student_ID = g.student_ID JOIN record.modules AS m ON g.moduleCode = m.moduleCode WHERE s.student_ID = ? AND g.grade >= 40");
            stmt.setString(1, selectedStudentID);

            // Execute the statement
            ResultSet rs = stmt.executeQuery();

            StringBuilder resultBuilder = new StringBuilder();
            textArea.clear();
            resultBuilder.append(String.format("%-15s %-30s %-10s\n", "Code", "Name", "Grade"));
            int rowCount = 0;
            while (rs.next()) {
                String moduleCode = rs.getString("moduleCode");
                String moduleName = rs.getString("moduleName");
                double grade = rs.getDouble("grade");

                resultBuilder.append(String.format("%-15s %-25s %.2f\n", moduleCode, moduleName, grade));
                rowCount++;
            }

            if (rowCount > 0) {
                textArea.setText(resultBuilder.toString());
                tab1textField.setText("Data is successfully displayed from the database");
            }else if(rowCount == 0){
                tab1textField.setText("No record found for Student ID: " + selectedStudentID);
            }
        } catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     Sets the student ComboBox with student IDs and names from the student database.
     */
    public void setStudentComboBox() {
        try {
            PreparedStatement stmt = model.getConnection().prepareStatement("SELECT student_ID, name FROM record.student");
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> studentList = FXCollections.observableArrayList();

            while (rs.next()) {
                String student_ID = rs.getString("student_ID");
                String name = rs.getString("name");
                String studentIdAndNames = student_ID + " - " + name;
                studentList.add(studentIdAndNames);
            }

            view.getStudentComboBox().setItems(studentList);
            view.getStudentComboBox().setPromptText("Student List");
        } catch (SQLException e){
            System.out.println("Please select a Student first");
        }
    }

    /**
     Sets the modules ComboBox with Module name and Module code from the student database.
     */
    public void setModulesComboBox(){
        try {
            PreparedStatement stmt = model.getConnection().prepareStatement("SELECT moduleName, moduleCode FROM record.modules;");
            ResultSet rs = stmt.executeQuery();

            ObservableList<String> moduleList = FXCollections.observableArrayList();

            while (rs.next()){
                String moduleName = rs.getString("moduleName");
                String moduleCode = rs.getString("moduleCode");
                String moduleInfo = moduleCode + " - " + moduleName;
                moduleList.add(moduleInfo);
            }

            view.getModulesComboBox().setItems(moduleList);
            view.getModulesComboBox().setPromptText("Select a module");
        } catch (SQLException e) {
            System.out.println("Please select a Student first");
        }
    }

    /**
     Adds a grade for a module to the database.
     @param modulesGradeField the TextField for the module's grade
     @param tab3textField the TextField to display success/error messages
     @throws SQLException if there was a problem with the database connection or query
     */
    public void addGrade(TextField modulesGradeField, TextField tab3textField) throws SQLException{
        if (modulesGradeField.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Information Not Added");
            alert.setContentText("Grade field is empty");
            alert.showAndWait();
            return;
        }

        String selectedStudent = view.getStudentComboBox().getValue();
        String selectedModule = view.getModulesComboBox().getValue();

        String[] studentParts = selectedStudent.split(" - ");
        String selectedStudentID = studentParts[0];

        String[] moduleParts = selectedModule.split(" - ");
        String selectedModuleCode = moduleParts[0];
        try{
            Double.parseDouble(modulesGradeField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Grade");
            alert.setContentText("Grade field should be a number");
            alert.showAndWait();
            return;
        }
        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("INSERT INTO record.grades (GradeId, student_id, moduleCode, grade) VALUES (NULL, ?, ?, ?)");
            stmt.setString(1, selectedStudentID);
            stmt.setString(2, selectedModuleCode);
            stmt.setDouble(3, Double.parseDouble(modulesGradeField.getText()));

            // Execute the statement to insert the data
            stmt.executeUpdate();

            // Update the text area to indicate success
            tab3textField.setText("Data is successfully added to the Grades database");

        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     Modifies the grade of a module in the database.
     @param modulesGradeField the TextField for the module's new grade
     @param tab3textField the TextField to display success/error messages
     */
    public void modifyGrade(TextField modulesGradeField, TextField tab3textField){
        if (modulesGradeField.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Information Not Added");
            alert.setContentText("Grade field is empty");
            alert.showAndWait();
            return;
        }

        String selectedStudent = view.getStudentComboBox().getValue();
        String selectedModule = view.getModulesComboBox().getValue();

        String[] studentParts = selectedStudent.split(" - ");
        String selectedStudentID = studentParts[0];

        String[] moduleParts = selectedModule.split(" - ");
        String selectedModuleCode = moduleParts[0];
        try{
            Double.parseDouble(modulesGradeField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Grade");
            alert.setContentText("Grade field should be a number");
            alert.showAndWait();
            return;
        }
        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("UPDATE record.grades SET grade = ? WHERE student_ID = ? AND moduleCode = ?");
            stmt.setDouble(1, Double.parseDouble(modulesGradeField.getText()));
            stmt.setString(2, selectedStudentID);
            stmt.setString(3, selectedModuleCode);

            // Execute the statement to insert the data
            stmt.executeUpdate();

            // Update the text area to indicate success
            tab3textField.setText("Data is successfully modified in the Grades table");

        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     Removes the grade of a module from the database.
     @param tab3textField the TextField to display success/error messages
     */
    public void removeGrade(TextField tab3textField){
        String selectedStudent = view.getStudentComboBox().getValue();
        String selectedModule = view.getModulesComboBox().getValue();

        String[] studentParts = selectedStudent.split(" - ");
        String selectedStudentID = studentParts[0];

        String[] moduleParts = selectedModule.split(" - ");
        String selectedModuleCode = moduleParts[0];
        try{
            PreparedStatement stmt = model.getConnection().prepareStatement("DELETE FROM record.grades WHERE student_ID = ? AND moduleCode = ?");
            stmt.setString(1, selectedStudentID);
            stmt.setString(2, selectedModuleCode);

            // Execute the statement to insert the data
            stmt.executeUpdate();

            // Update the text area to indicate success
            tab3textField.setText("Data is successfully removed from the Grades table");

        }catch (SQLException e) {
            // Show an error message if there was a problem with the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Database Error");
            alert.setContentText("There was an error accessing the database: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
