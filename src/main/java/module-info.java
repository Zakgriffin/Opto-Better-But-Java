module com.zakgriffin.opto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.zakgriffin.opto to javafx.fxml;
    exports com.zakgriffin.opto;
    exports com.zakgriffin.opto.types;
    opens com.zakgriffin.opto.types to javafx.fxml;
    exports com.zakgriffin.opto.objects;
    opens com.zakgriffin.opto.objects to javafx.fxml;
    exports com.zakgriffin.opto.view;
    opens com.zakgriffin.opto.view to javafx.fxml;
}