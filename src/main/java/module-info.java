module com.example.finalassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;



    opens com.example.finalassignment to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Main;
    opens Main to javafx.fxml;
}