package View;

import Controller.studentController;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

/**
 This interface represents the view for the student record application.
 */
public interface StudentViewInterface {
    /**
     * Initializes the user interface for the Student Record Management System.
     * It sets up the necessary UI components for the application.
     */
    void initStudentRecordUI();

    /**
     * Initialise the JavaFX Scene object that represents the entire user interface for the Student Record application.
     * @return a JavaFX Scene object representing the entire user interface for the Student Record application.
     */
    Scene getScene();

    /**
     * Sets up the event handler for all the buttons in the application
     */
    void setup();

    /**
     * Sets the student controller for this view.
     * @param studentController the student controller to be set
     */
    void setStudentController(studentController studentController);

    /**
     * @return the student combo box
     */
    ComboBox<String> getStudentComboBox();

    /**
     * @return the modules combo box
     */
    ComboBox<String> getModulesComboBox();

    /**
     * @return the search combo box
     */
    ComboBox<String> getSearchComboBox();

}
