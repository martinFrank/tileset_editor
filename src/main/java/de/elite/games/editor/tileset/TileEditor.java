package de.elite.games.editor.tileset;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TileEditor extends Application {

    private static final Logger LOGGGER = LoggerFactory.getLogger(TileEditor.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/tileset_editor.fxml"));
        GridPane root = fxmlLoader.load();
        TilesetController tilesetController = (TilesetController) fxmlLoader.getController();
        tilesetController.initListListener();

        LOGGGER.debug("TilesetController: {}", tilesetController);

        primaryStage.setTitle("Tileset editor");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();


    }
}

