//Marco Castro
//1224378025
//Used IntelliJ IDEA CE yay
package com.marco.hearthealth.hearthhealthimagingapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class HeartHealthImagingApp extends Application {
    private final Map<String, PatientData> patientDatabase = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        //Main Screen
        Label welcomeLabel = new Label("Welcome to the Heart Health Imaging and Recording System");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button intakeButton = MenuButton("Patient Intake");
        Button scanButton = MenuButton("CT Scan Tech View");
        Button detailsButton = MenuButton("Patient View");
        VBox menuLayout = new VBox(15, welcomeLabel, intakeButton, scanButton, detailsButton);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(menuLayout, 400, 300);
        primaryStage.setTitle("Heart Health System");
        primaryStage.setScene(mainScene);
        primaryStage.show();

        intakeButton.setOnAction(e -> PatientIntake(primaryStage, mainScene));
        scanButton.setOnAction(e -> ScanView(primaryStage, mainScene));
        detailsButton.setOnAction(e -> PatientDetails(primaryStage, mainScene));
    }
    private Button MenuButton(String label) {
        Button button = new Button(label);
        button.setPrefWidth(200);
        button.setStyle("-fx-background-color: #005bbb; -fx-text-fill: white; -fx-font-size: 14px;");
        return button;
    }
    //Patient intake screen
    private void PatientIntake(Stage primaryStage, Scene mainScene) {
        //Patient intake screen
        Label formTitle = new Label("Patient Intake Form");
        formTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        formTitle.setAlignment(Pos.CENTER);
        GridPane formLayout = createGrid();
        TextField idField = addFormRow("Patient ID:", formLayout, 0);
        TextField firstNameField = addFormRow("First Name:", formLayout, 1);
        TextField lastNameField = addFormRow("Last Name:", formLayout, 2);
        TextField emailField = addFormRow("Email:", formLayout, 3);
        TextField phoneField = addFormRow("Phone Number:", formLayout, 4);
        TextField historyField = addFormRow("Health History:", formLayout, 5);
        TextField insuranceField = addFormRow("Insurance ID:", formLayout, 6);

        //Save and back button, made a back button just in case
        Button saveButton = new Button("Save");
        Button backButton = new Button("Back");
        HBox buttonBox = new HBox(10, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox mainLayout = new VBox(20, formTitle, formLayout, buttonBox);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        Scene intakeScene = new Scene(mainLayout, 450, 500);
        primaryStage.setScene(intakeScene);
        saveButton.setOnAction(e -> PatientData(
                idField, firstNameField, lastNameField, emailField, phoneField, historyField, insuranceField
        ));
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
    }
    //CT scan view
    private void ScanView(Stage primaryStage, Scene mainScene) {
        Label title = new Label("CT Scan Technician View");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        title.setAlignment(Pos.CENTER);

        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(20));
        formLayout.setHgap(10);
        formLayout.setVgap(10);

        TextField idField = addFormRow("Patient ID:", formLayout, 0);
        TextField cacField = addFormRow("The total Agatston CAC score:", formLayout, 1);

        Label vesselLabel = new Label("Vessel Level Agatston CAC Score");
        vesselLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        formLayout.add(vesselLabel, 0, 2, 2, 1);

        TextField lmField = addFormRow("LM:", formLayout, 3);
        TextField ladField = addFormRow("LAD:", formLayout, 4);
        TextField lcxField = addFormRow("LCX:", formLayout, 5);
        TextField rcaField = addFormRow("RCA:", formLayout, 6);
        TextField pdaField = addFormRow("PDA:", formLayout, 7);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #005bbb; -fx-text-fill: white; -fx-font-size: 14px;"); //Used white instead of black
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #cccccc; -fx-font-size: 14px;");

        HBox buttonBox = new HBox(10, backButton, saveButton);
        buttonBox.setAlignment(Pos.CENTER);
        formLayout.add(buttonBox, 1, 8);
        VBox mainLayout = new VBox(20, title, formLayout);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);


        Scene scanScene = new Scene(mainLayout, 500, 600);
        primaryStage.setScene(scanScene);


        saveButton.setOnAction(e -> saveScanData(idField, cacField, lmField, ladField, lcxField, rcaField, pdaField));
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
    }
    //Patient details screen
    private void PatientDetails(Stage primaryStage, Scene mainScene) {
        Label title = new Label("Patient view");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label patientNameLabel = new Label("Hello <Patient Name>");
        patientNameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        patientNameLabel.setVisible(false);

        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(20));
        formLayout.setHgap(10);
        formLayout.setVgap(10);

        TextField idField = addFormRow("Patient ID:", formLayout, 0);
        formLayout.add(patientNameLabel, 0, 1, 2, 1);

        Label totalCACLabel = new Label("The total Agatston CAC score:");
        TextField totalCACField = new TextField();
        totalCACField.setEditable(false);
        totalCACField.setPrefWidth(300);
        formLayout.add(totalCACLabel, 0, 2);
        formLayout.add(totalCACField, 1, 2);


        TextField lmField = addFormRow("LM:", formLayout, 3);
        TextField ladField = addFormRow("LAD:", formLayout, 4);
        TextField lcxField = addFormRow("LCX:", formLayout, 5);
        TextField rcaField = addFormRow("RCA:", formLayout, 6);
        TextField pdaField = addFormRow("PDA:", formLayout, 7);


        Button viewButton = new Button("View");
        Button backButton = new Button("Back");
        HBox buttonBox = new HBox(10, viewButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        formLayout.add(buttonBox, 0, 8, 2, 1);
        VBox mainLayout = new VBox(20, title, formLayout);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Scene detailsScene = new Scene(mainLayout, 500, 600);
        primaryStage.setScene(detailsScene);

        viewButton.setOnAction(e -> {
            String patientId = idField.getText();
            PatientData patient = patientDatabase.get(patientId);
            if (patient == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Patient not found.");
                alert.showAndWait();
                patientNameLabel.setVisible(false);
                totalCACField.clear();
                lmField.clear();
                ladField.clear();
                lcxField.clear();
                rcaField.clear();
                pdaField.clear();
            } else {
                patientNameLabel.setText("Hello " + patient.getFirstName() + " " + patient.getLastName());
                patientNameLabel.setVisible(true);
                if (patient.getTotalCAC() != null) {
                    totalCACField.setText(patient.getTotalCAC());
                    lmField.setText(patient.getLM());
                    ladField.setText(patient.getLAD());
                    lcxField.setText(patient.getLCX());
                    rcaField.setText(patient.getRCA());
                    pdaField.setText(patient.getPDA());
                } else {
                    totalCACField.setText("No data available");
                    lmField.clear();
                    ladField.clear();
                    lcxField.clear();
                    rcaField.clear();
                    pdaField.clear();
                }
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
    }
    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }
    private TextField addFormRow(String labelText, GridPane grid, int row) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        grid.add(label, 0, row);
        grid.add(textField, 1, row);
        return textField;
    }
    //Patient intake data
    private void PatientData(TextField id, TextField firstName, TextField lastName, TextField email,
                             TextField phone, TextField history, TextField insurance) {
        String patientId = id.getText();
        if (patientId.isBlank() || firstName.getText().isBlank() || lastName.getText().isBlank()) {
            showAlert("Error", "Patient ID, First Name, and Last Name are required.");
        } else {
            PatientData data = new PatientData(
                    firstName.getText(), lastName.getText(), email.getText(), phone.getText(), history.getText(), insurance.getText()
            );
            patientDatabase.put(patientId, data);
            showAlert("Success", "Patient details saved successfully!");
        }
    }
    //Saves scan data
    private void saveScanData(TextField id, TextField... fields) {
        PatientData patient = patientDatabase.get(id.getText());
        if (patient == null) {
            showAlert("Error", "No patient found with the given ID.");
        } else {
            patient.setScanData(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(),
                    fields[4].getText(), fields[5].getText());
            showAlert("Success", "Scan data saved successfully!");
        }
    }
    private void PatientDetails(TextField id, Label greeting, TextField... fields) {
        PatientData patient = patientDatabase.get(id.getText());
        if (patient == null) {
            showAlert("Error", "Patient not found.");
        } else {
            greeting.setText("Welcome " + patient.getFirstName() + " " + patient.getLastName());
            greeting.setVisible(true);
            fields[0].setText(patient.getTotalCAC());
            fields[1].setText(patient.getLM());
            fields[2].setText(patient.getLAD());
            fields[3].setText(patient.getLCX());
            fields[4].setText(patient.getRCA());
            fields[5].setText(patient.getPDA());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    //Patient information
    static class PatientData {
        private final String firstName, lastName, email, phone, history, insurance;
        private String totalCAC, lm, lad, lcx, rca, pda;

        public PatientData(String firstName, String lastName, String email, String phone, String history, String insurance) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.history = history;
            this.insurance = insurance;
        }

        public void setScanData(String totalCAC, String lm, String lad, String lcx, String rca, String pda) {
            this.totalCAC = totalCAC;
            this.lm = lm;
            this.lad = lad;
            this.lcx = lcx;
            this.rca = rca;
            this.pda = pda;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getTotalCAC() {
            return totalCAC;
        }

        public String getLM() {
            return lm;
        }

        public String getLAD() {
            return lad;
        }

        public String getLCX() {
            return lcx;
        }

        public String getRCA() {
            return rca;
        }

        public String getPDA() {
            return pda;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}