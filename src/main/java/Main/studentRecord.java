package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import Controller.studentController;
import View.studentView;

/**

 The studentRecord class is the main class of the application that extends the JavaFX Application class.
 */
public class studentRecord extends Application {

    /**
     The start() method is called by the JavaFX framework to create and display the application window.
     It creates the view, and controller objects for the application and sets up the user interface by calling the view's setup() method.
     @param stage The main application window
     */
    @Override
    public void start(Stage stage){
        studentView view = new studentView();
        studentController controller = new studentController(view);

        view.setStudentController(controller);

        view.setup();
        stage.setScene(view.getScene());
        stage.setTitle("MTU Student Record");
        stage.show();
    }

    /**
     The main() method is the entry point of the application.
     @param args The command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch();
    }
}
