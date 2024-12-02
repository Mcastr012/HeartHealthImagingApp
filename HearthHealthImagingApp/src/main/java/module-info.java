module com.marco.hearthealth.hearthhealthimagingapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marco.hearthealth.hearthhealthimagingapp to javafx.fxml;
    exports com.marco.hearthealth.hearthhealthimagingapp;
}