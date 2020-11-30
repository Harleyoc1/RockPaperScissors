package com.harleyoconnor.rockpaperscissors;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.javautilities.IntegerUtils;
import com.harleyoconnor.rockpaperscissors.utils.InterfaceUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * @author Harley O'Connor
 */
public final class RockPaperScissors extends Application {

    private static RockPaperScissors INSTANCE; // Effectively final instance variable.

    private final StackPane view = new StackPane(); // Initialise main view.
    private final Scene scene = new Scene(this.view, 700, 400); // Initialise the scene.

    private Label resultLabel;

    private int playerScore = 0;
    private int computerScore = 0;

    private Label playerScoreLabel;
    private Label computerScoreLabel;

    public RockPaperScissors() {
        INSTANCE = this;
    }

    @Override
    public void start (final Stage primaryStage) {
        final VBox scoreBoard = new VBox(5);
        final VBox verticalView = new VBox(15);

        this.view.getStylesheets().add("file:" + FileUtils.getFile("stylesheets/default.css").getPath());

        final Font header = Font.font("arial", FontWeight.BOLD, 16);
        final Font subLine = Font.font("arial", FontWeight.BOLD, 14);
        final Font body = Font.font("arial", FontWeight.NORMAL, 16);

        this.playerScoreLabel = InterfaceUtils.createLabel(Integer.toString(this.playerScore), subLine);
        this.computerScoreLabel = InterfaceUtils.createLabel(Integer.toString(this.computerScore), subLine);
        this.resultLabel = InterfaceUtils.createLabel("", body);

        scoreBoard.getChildren().addAll(
                InterfaceUtils.centraliseElementsInBox(new HBox(10),
                        InterfaceUtils.addElementsToPane(new HBox(0),
                                InterfaceUtils.setSizeOfNode(InterfaceUtils.createLabel("Player", header), 30, 0)),
                        InterfaceUtils.addElementsToPane(new HBox(0),
                                InterfaceUtils.setSizeOfNode(InterfaceUtils.createLabel("Computer", header), 30, 0))),
                InterfaceUtils.centraliseElementsInBox(new HBox(10),
                        InterfaceUtils.addElementsToPane(new HBox(0),
                                InterfaceUtils.setSizeOfNode(this.playerScoreLabel, 30, 0)),
                        InterfaceUtils.addElementsToPane(InterfaceUtils.setSizeOfNode(new HBox(0), 30, 0),
                                InterfaceUtils.setSizeOfNode(this.computerScoreLabel, 30, 0))));

        verticalView.getChildren().addAll(
                scoreBoard,
                InterfaceUtils.addElementsToPane(InterfaceUtils.centraliseElementsInBox(new HBox(5),
                        this.createSelectionButton(SelectionType.ROCK),
                        this.createSelectionButton(SelectionType.PAPER),
                        this.createSelectionButton(SelectionType.SCISSORS))),
                InterfaceUtils.addElementsToPane(InterfaceUtils.centraliseElementsInBox(new HBox(0), this.resultLabel)));

        verticalView.setPadding(new Insets(20));

        this.view.getChildren().add(verticalView);

        primaryStage.setScene(this.scene);
        primaryStage.show();
    }

    private Button createSelectionButton (final SelectionType selectionType) {
        final Button selectionButton = new Button("", this.getImageView(selectionType.toString().toLowerCase()));
        selectionButton.getStyleClass().add("selectionButton");
        selectionButton.setOnAction(event -> this.onSelectionMade(selectionType));
        return selectionButton;
    }

    private ImageView getImageView (final String fileName) {
        final Image paperImage = new Image("file:" + FileUtils.getInternalPath("textures/" + fileName + ".png"));
        final ImageView paperImageView = new ImageView(paperImage);

        paperImageView.setFitHeight(200);
        paperImageView.setFitWidth(200);

        return paperImageView;
    }

    private void onSelectionMade (final SelectionType selectionType) {
        final SelectionType randomSelection = SelectionType.values()[IntegerUtils.getRandomIntBetween(0, SelectionType.values().length - 1)];

        if (selectionType == randomSelection)
            this.resultLabel.setText("You both selected " + selectionType.toString().toLowerCase() + "! Draw!");
        else if (selectionType.beats(randomSelection)) {
            this.resultLabel.setText("Computer selected " + randomSelection.toString().toLowerCase() + ". You won!");
            this.playCorrectSound();
            this.playerScore++;
        } else {
            this.resultLabel.setText("Computer selected " + randomSelection.toString().toLowerCase() + ". You lost!");
            this.playIncorrectSound();
            this.computerScore++;
        }

        this.updateScores();
    }

    private void updateScores () {
        this.playerScoreLabel.setText(Integer.toString(this.playerScore));
        this.computerScoreLabel.setText(Integer.toString(this.computerScore));
    }

    private void playCorrectSound () {
        this.playSound("correct");
    }

    private void playIncorrectSound () {
        this.playSound("incorrect");
    }

    private void playSound (final String fileName) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(FileUtils.getFile("sound/" + fileName + ".wav").getAbsoluteFile());
            final Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-10.f);
            clip.start();
        } catch(Exception e) {
            System.err.println("Error playing sound.");
            e.printStackTrace();
        }
    }

    public static void main (final String[] args) {
        launch(args);
    }

    public static RockPaperScissors getInstance () {
        return INSTANCE;
    }

}
