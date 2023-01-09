module com.zakgriffin.opto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.zakgriffin.opto to javafx.fxml;
    exports com.zakgriffin.opto;
    exports com.zakgriffin.opto.types;
    opens com.zakgriffin.opto.types to javafx.fxml;
    exports com.zakgriffin.opto.objects;
    opens com.zakgriffin.opto.objects to javafx.fxml;
    exports com.zakgriffin.opto.views;
    opens com.zakgriffin.opto.views to javafx.fxml;
    exports com.zakgriffin.opto.reactivity;
    opens com.zakgriffin.opto.reactivity to javafx.fxml;
    exports com.zakgriffin.opto.reactivity.reversible;
    opens com.zakgriffin.opto.reactivity.reversible to javafx.fxml;
}