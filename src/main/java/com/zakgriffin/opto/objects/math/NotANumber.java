package com.zakgriffin.opto.objects.math;

import com.zakgriffin.opto.LookupBox;
import com.zakgriffin.opto.objects.O;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class NotANumber implements O {
    @Override
    public Node getNormalView(LookupBox owningLookupBox) {
        TextField textField = owningLookupBox.getTextField();
        textField.setText("NaN");
        return textField;
    }
}
