module com.zakgriffin.opto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.zakgriffin.opto to javafx.fxml;
    exports com.zakgriffin.opto;
    exports com.zakgriffin.opto.types;
    opens com.zakgriffin.opto.types to javafx.fxml;
    exports com.zakgriffin.opto.objects;
    opens com.zakgriffin.opto.objects to javafx.fxml;
}