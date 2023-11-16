package View;

import Controller.studentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;
import java.sql.SQLException;

/**
 The view for the student record application.
 */
public class studentView implements StudentViewInterface{
    private Label namelabel, idLabel, dobLabel, currentSemesterLabel, moduleNameLabel, moduleCodeLabel, semesterLabel, modulesGradeLabel, studentComboboxLabel, modulesComboboxLabel, searchStudentLabel;
    private TextField NameField, IdField, currentSemesterField, tab1TextField, moduleCodeField, moduleNameField, semesterField, tab2textField, modulesGradeField, tab3textField;
    private Button addBtn, removeBtn, listBtn, removeModuleBtn, addModuleBtn, addGrade, removeGrade,modifyGrade, searchStudentBtn, simulateMemoryLeak;
    private TextArea textArea;
    private DatePicker dobField;
    private studentController studentController;
    public ComboBox<String> searchCombobox, studentCombobox, modulesCombobox;

    /**
     Constructor for the student view. Initializes the user interface.
     */
    public studentView(){
        initStudentRecordUI();
    }

    /**
     Initializes the user interface for the Student Record Management System.
     It sets up the necessary UI components for the application.
     */
    public void initStudentRecordUI() {

        //===========================
        //----------Tab 1------------
        //===========================

        namelabel = new Label("Enter Name ");
        // Name field
        NameField = new TextField();

        // Id label
        idLabel = new Label("Student Id");
        // ID field
        IdField = new TextField();

        // DOB label
        dobLabel = new Label("Date of Birth");
        //DOB field
        dobField = new DatePicker();

        // Semester label
        currentSemesterLabel = new Label("Current Semester");
        // Semester field
        currentSemesterField = new TextField();


        // Buttons
        addBtn = new Button("Add");
        removeBtn = new Button("Remove");
        listBtn = new Button("List");
        simulateMemoryLeak = new Button("Simulate Memory leak");

        searchStudentLabel = new Label("Student Search");
        searchCombobox = new ComboBox<>();
        searchCombobox.setPromptText("Select student");
        searchStudentBtn = new Button("Search");

        // Text field
        tab1TextField = new TextField();
        tab1TextField.setEditable(false);
        tab1TextField.setPrefWidth(400);

        // Text area
        textArea = new TextArea();
        textArea.setEditable(false);

        //===========================
        //----------Tab 2------------
        //===========================

        // Module label
        moduleCodeLabel = new Label("Module Code ");
        // Module field
        moduleCodeField = new TextField();

        // Grade label
        moduleNameLabel = new Label("Module Name ");
        // Grade field
        moduleNameField = new TextField();

        // Semester label
        semesterLabel = new Label("Semester");
        // Semester field
        semesterField = new TextField();

        // Add Module button
        addModuleBtn = new Button("Add Module");
        removeModuleBtn = new Button("Remove Module");

        tab2textField = new TextField();
        tab2textField.setEditable(false);
        tab2textField.setPrefWidth(400);

        // tab 3

        studentComboboxLabel = new Label("Select Student");
        studentCombobox = new ComboBox<>();
        modulesComboboxLabel = new Label("Select Modules");
        modulesCombobox = new ComboBox<>();
        modulesGradeLabel = new Label("Enter grade (-1 for NP)");
        modulesGradeField = new TextField();


        tab3textField = new TextField();

        // Buttons
        addGrade = new Button("Add grade");
        modifyGrade = new Button("Modify grade");
        removeGrade = new Button("Remove grade");
    }

    /**
     Initialise the JavaFX Scene object that represents the entire user interface for the Student Record application.
     @return a JavaFX Scene object representing the entire user interface for the Student Record application.
     */
    public Scene getScene(){
        //grid pane
        GridPane gridPane = new GridPane();
        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();

        gridPane.add(namelabel, 0, 0, 1, 1);
        gridPane.add(NameField, 1, 0, 1, 1);
        gridPane.add(idLabel, 0, 1, 1, 1);
        gridPane.add(IdField, 1, 1, 1, 1);
        gridPane.add(dobLabel, 0, 2, 1, 1);
        gridPane.add(dobField, 1, 2, 1, 1);
        gridPane.add(currentSemesterLabel, 0, 3);
        gridPane.add(currentSemesterField, 1, 3);

        gridPane1.add(addBtn, 0, 3, 1, 1);
        gridPane1.add(removeBtn, 1, 3, 1, 1);
        gridPane1.add(listBtn, 2, 3, 1, 1);

        gridPane2.add(tab1TextField, 0, 0, 1, 1);
        gridPane2.add(textArea, 0, 1, 1, 1);

        // Spacing and alignment
        gridPane.setAlignment(Pos.CENTER);
        gridPane1.setAlignment(Pos.CENTER);
        gridPane2.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setVgap(5);
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);

        HBox searchHBox = new HBox(searchStudentLabel, searchCombobox, searchStudentBtn);
        searchHBox.setSpacing(10);
        searchHBox.setAlignment(Pos.CENTER);

        // VBox
        VBox tab1VBox = new VBox();
        tab1VBox.setSpacing(5.0);
        tab1VBox.setPadding(new Insets(5, 5, 5, 5));
        tab1VBox.getChildren().addAll(gridPane, gridPane1, searchHBox, gridPane2, simulateMemoryLeak);

        Tab tab1 = new Tab("Add Student");
        tab1.setClosable(false);
        tab1.setContent(tab1VBox);
        //GridPane
        GridPane tab2GridPane = new GridPane();
        tab2GridPane.add(moduleCodeLabel, 0, 1);
        tab2GridPane.add(moduleCodeField, 1, 1);
        tab2GridPane.add(moduleNameLabel, 0, 2);
        tab2GridPane.add(moduleNameField, 1, 2);
        tab2GridPane.add(semesterLabel, 0, 3);
        tab2GridPane.add(semesterField, 1, 3);
        tab2GridPane.setAlignment(Pos.CENTER);
        tab2GridPane.setHgap(10);
        tab2GridPane.setVgap(10);

        // HBox for the second tab
        HBox moduleBox = new HBox(addModuleBtn, removeModuleBtn);
        moduleBox.setSpacing(10);
        moduleBox.setAlignment(Pos.CENTER);

        // VBox for the second tab
        VBox tab2VBox = new VBox(tab2GridPane, moduleBox, tab2textField);
        tab2VBox.setSpacing(10);
        tab2VBox.setPadding(new Insets(20));
        tab2VBox.setAlignment(Pos.TOP_CENTER);

        Tab tab2 = new Tab("Module Records");
        tab2.setClosable(false);
        tab2.setContent(tab2VBox);

        // Tab 3
        GridPane tab3GridPane = new GridPane();
        tab3GridPane.add(studentComboboxLabel, 0, 0);
        tab3GridPane.add(studentCombobox, 1, 0);
        tab3GridPane.add(modulesComboboxLabel, 0, 1);
        tab3GridPane.add(modulesCombobox, 1, 1);
        tab3GridPane.add(modulesGradeLabel, 0, 2);
        tab3GridPane.add(modulesGradeField, 1, 2);

        tab3GridPane.setHgap(10);
        tab3GridPane.setVgap(10);

        HBox tab3Hbox = new HBox(addGrade, modifyGrade, removeGrade);
        tab3Hbox.setSpacing(10);
        tab3Hbox.setAlignment(Pos.CENTER);

        VBox tab3VBox = new VBox(tab3GridPane, tab3Hbox, tab3textField);
        tab3VBox.setSpacing(10);
        tab3VBox.setPadding(new Insets(20));
        tab3VBox.setAlignment(Pos.TOP_CENTER);

        Tab tab3 = new Tab("Grades");
        tab3.setClosable(false);
        tab3.setContent(tab3VBox);

        // create a new TabPane
        TabPane tabPane = new TabPane();

        tabPane.getTabs().addAll(tab1, tab2, tab3);

        // add tabPane to borderPane
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        //Properties of the stage

        return new Scene(root, 500, 600);
    }

    /**
     * Sets up the event handler for all the buttons in the application
     */
    public void setup() {
        try {
            addBtn.setOnAction(e -> studentController.addInfo(NameField, IdField, dobField, currentSemesterField, tab1TextField));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            removeBtn.setOnAction(e -> studentController.removeInfo(IdField, tab1TextField));
        }catch (Exception e) {
            e.printStackTrace();
        }

        listBtn.setOnAction(e -> studentController.listStudent(textArea, tab1TextField));

        simulateMemoryLeak.setOnAction(e -> studentController.simulateMemoryLeak());

        addModuleBtn.setOnAction(e -> studentController.addModuleInfo(moduleCodeField, moduleNameField, semesterField, tab2textField) );

        removeModuleBtn.setOnAction(e -> studentController.removeModuleInfo(moduleCodeField, tab2textField));

        studentController.setSearchComboBox();

        studentController.setStudentComboBox();
        studentController.setModulesComboBox();
        studentCombobox.setOnAction(e -> studentController.setStudentComboBox());
        modulesCombobox.setOnAction(e -> studentController.setModulesComboBox());

        addGrade.setOnAction(e -> {
            try {
                studentController.addGrade(modulesGradeField, tab3textField);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        modifyGrade.setOnAction(e -> studentController.modifyGrade(modulesGradeField, tab3textField));
        removeGrade.setOnAction(e -> studentController.removeGrade(tab3textField));

        studentController.setSearchComboBox();
        searchStudentBtn.setOnAction(e -> studentController.searchStudent(tab1TextField, textArea));
    }

    /**
     Sets the student controller for this view.
     @param studentController the student controller to be set
     */
    public void setStudentController(studentController studentController) {
        this.studentController = studentController;
    }

    /**
     @return the student combo box
     */
    public ComboBox<String> getStudentComboBox() {
        return studentCombobox;
    }

    /**
     @return the modules combo box
     */
    public ComboBox<String> getModulesComboBox() {
        return modulesCombobox;
    }

    /**
     @return the search combo box
     */
    public ComboBox<String> getSearchComboBox() {
        return searchCombobox;
    }

}
