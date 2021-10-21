module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;

    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;
    exports ku.cs.fxrouter;
    opens ku.cs.fxrouter to javafx.fxml;
//    opens ku.cs.model to javafx.fxml;
}
