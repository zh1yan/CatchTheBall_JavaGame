package Cartoon;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Here's Cartoon! Your first JavaFX assignment!
 * Before you start coding your Cartoon, take a look at
 * the lecture slides and JavaFX Guide for all the
 * information you'll need (and more!).
 *
 * Please put your overall comments for the project here.
 *
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
      PaneOrganizer organizer = new PaneOrganizer();
      Scene scene = new Scene(organizer.getRoot(),
      Constants.APP_WIDTH, Constants.APP_HEIGHT);
      stage.setScene(scene);
      stage.setTitle("Catch The Ball");
      stage.show();
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}

