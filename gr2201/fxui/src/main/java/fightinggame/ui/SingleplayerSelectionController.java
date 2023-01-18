package fightinggame.ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;

public class SingleplayerSelectionController extends SceneController {
    @FXML private Button lockIn, goBack;
    @FXML private GridPane characterSelectGrid;
    @FXML private ImageView characterSelected;
    private ImageView characterSelectedImage;
    private Media audioSelect;
    private Media audioGame;
    private String path;
    
    @FXML
    private void initialize() {
        this.path = "gr2201//fxui/src/main/resources/fightinggame/ui/";		
        
        lockIn.setDisable(true);
        try {
            audioSelect = new Media(new File(path + "Audio/CharacterSelect.wav")
                    .toURI().toString());
            audioGame = new Media(
                    new File(path + "Audio/Game.wav").toURI().toString());
            mainAudioPlayer = new MediaPlayer(audioSelect);
            mainAudioPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mainAudioPlayer.seek(Duration.ZERO);
                }
            });
            mainAudioPlayer.setVolume(0.1);
            mainAudioPlayer.play();

        } catch (MediaException e) {
            System.out.println("You either dont have the correct Media Codec or the audio files did not load in.s You cant play audio. Error: " + e);
        }
    }

    public String getCharacterSelectedId() {
        return this.characterSelectedImage.getId();
    }

    public ImageView getCharacterSelected() {
        return this.characterSelected;
    }

    private void resetCharacterImageOpacity() {
        for (Node imageView : characterSelectGrid.getChildren()) {
            imageView.setOpacity(1);
        }
    }

    @FXML
    private void handleSelectCharacter(MouseEvent event) {
        resetCharacterImageOpacity();
        ImageView image = (ImageView) event.getSource();
        this.characterSelectedImage = image;
        characterSelected.setImage(new Image(getClass().getResource(image.getId() + "SplashArt.png").toString()));
        image.setOpacity(0.7);
        lockIn.setDisable(false);
    }

    @FXML
    private void handleLockIn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WorldCanvas.fxml"));
            Parent root = loader.load();
            SingleplayerGameController singleplayerGameController = loader.getController();
            singleplayerGameController.setUser(super.getUser());
            singleplayerGameController.loadWorld(getCharacterSelectedId(), null, path);
            super.changeSceneFullscreen("NTNU Fighterz", root, event);

            if (mainAudioPlayer != null) {
                mainAudioPlayer.stop();
            }

            try {
                mainAudioPlayer = new MediaPlayer(audioGame);
                mainAudioPlayer.setOnEndOfMedia(new Runnable() {
                    public void run() {
                        mainAudioPlayer.seek(Duration.ZERO);
                    }
                });
                mainAudioPlayer.setVolume(0.1);
                mainAudioPlayer.play();
    
            } catch (MediaException e) {
                System.out.println("You either dont have the correct Media Codec or the audio files did not load in.s You cant play audio. Error: " + e);
            }

        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Main menu page could not be found.");
            e.printStackTrace();

        } catch (NullPointerException e) {
            showError("Error: Not a playable character", "This character is not unlocked or playable yet.");
        }
    
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        if (mainAudioPlayer != null) {
            mainAudioPlayer.stop();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root;
        try {
            root = loader.load();
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.setUser(super.getUser());
            super.changeScene("NTNU Fighterz", root, event);
        } catch (IOException e) {
            showError("Error: Invalid go back path",
                    "Something went wrong and Main menu page could not be found.");
            e.printStackTrace();
        }
    }
}
