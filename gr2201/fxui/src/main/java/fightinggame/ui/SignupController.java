package fightinggame.ui;

import fightinggame.users.User;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController extends SceneController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button createAccount, goBack;
    @FXML
    private Label nonValidCredentials;
    @FXML
    private Label nonMatchingPasswords;
    private RemoteModelAccess remoteModelAccess = new RemoteModelAccess();

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        User tempUser = remoteModelAccess.postUser(username, password, confirmPassword);

        if (tempUser == null) {
            if (password.equals(confirmPassword)) {
                nonValidCredentials.setText("Username already exists or password/username do not fit criteria.");
            } else {
                nonMatchingPasswords.setText("Passwords do not match.");
            }
            return;
        }

        showTutorial();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUser(tempUser);
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid main menu path",
                    "Something went wrong and main menu page could not be found.");
            e.printStackTrace();
        }
    }

    private void showTutorial() {
        TutorialComponent tutorial = new TutorialComponent();
        tutorial.popupTutorial();
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root;
        try {
            root = loader.load();
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Login page could not be found.");
            e.printStackTrace();
        }
    }

}
