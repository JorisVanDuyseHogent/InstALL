module com.jorisduyse.install {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;

    requires org.kordamp.bootstrapfx.core;

    exports com.jorisduyse.install.main;
    exports com.jorisduyse.install.gui;
    exports com.jorisduyse.install.domain;
    exports com.jorisduyse.install.resources;

//    opens com.jorisduyse.install to javafx.fxml;
//    exports com.jorisduyse.install;
//    exports com.jorisduyse.install.main;
//    opens com.jorisduyse.install.main to javafx.fxml;
}