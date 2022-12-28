module com.zakgriffin.opto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.zakgriffin.opto to javafx.fxml;
    exports com.zakgriffin.opto;
}