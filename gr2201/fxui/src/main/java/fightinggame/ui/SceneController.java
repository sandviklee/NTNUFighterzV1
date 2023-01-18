package fightinggame.ui;

import fightinggame.users.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * The {@code SceneController} class will is a base controller that gives all child classes methods:
 * {@link #changeScene(String, Parent, ActionEvent)}, {@link #changeSceneFullscreen(String, Parent, ActionEvent)}
 * {@link #changeSceneMouseEvent(String, Parent, MouseEvent)}
 * The class will also have a {@code User}, this is the user currently logged in.
 */
public class SceneController {
    private User user;
    public static MediaPlayer mainAudioPlayer;

    protected User getUser(){
        return this.user;
    }

    protected void setUser(User user){
        this.user = user;
    }

    /**
     * Changes scenes with the corresponding root
     * 
     * @param title  of the page
     * @param root   of the xml
     * @param event  that fires of the change
     */
    protected void changeScene(String title, Parent root, ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setWidth(1280);
        stage.setHeight(760);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The method changeSceneFullscreen sets the screen to fullscreen.
     * 
     * @param title  of the page
     * @param root   of the xml
     * @param event  that fires of the change
     */
    protected void changeSceneFullscreen(String title, Parent root, ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.setScene(scene);
        stage.show();
       
    }

    /**
     * Like {@link #changeScene(String, Parent, ActionEvent)} but use a MouseEvent
     * 
     * @param title  of the page
     * @param root   of the xml
     * @param event  that fires of the change
     */
    protected void changeSceneMouseEvent(String title, Parent root, MouseEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setWidth(1280);
        stage.setHeight(760);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Makes an popup appear with the error given
     * @param headerText   of the popup
     * @param contentText  of the popup
     */
    protected void showError(String headerText, String contentText){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
