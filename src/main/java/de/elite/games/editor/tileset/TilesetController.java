package de.elite.games.editor.tileset;

import de.elite.games.editor.tileset.tileset.Tileset;
import de.elite.games.editor.tileset.tileset.TilesetFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TilesetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TilesetController.class);

    private final TilesetFactory tilesetFactory = new TilesetFactory();
    private Tileset selected;

    @FXML
    private ListView<Tileset> tilesetList;

    @FXML
    private Button addTilesetButton;


    @FXML
    protected void handleAddTilesetButtonAction(ActionEvent event) {
        Window owner = addTilesetButton.getScene().getWindow();
        LOGGER.debug("add tileset button pressed ");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tileset Image File");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            LOGGER.debug("opened file: {}", file);
            try {
                Tileset tileset = tilesetFactory.fromFile(file);
                tilesetList.getItems().add(tileset);
            } catch (IOException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Tileset creation error!", "Error creating new Tileset");
            }
        }
    }

    public void handleRemoveTilesetButtonAction(ActionEvent event) {
        LOGGER.debug("remove tileset button pressed ");
    }

    void initListListener() {
        tilesetList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tileset>() {
            @Override
            public void changed(ObservableValue<? extends Tileset> observable, Tileset oldValue, Tileset newValue) {
                LOGGER.debug("oldValue: {} ", oldValue);
                LOGGER.debug("newValue: {} ", newValue);
                selected = newValue;

            }
        });

    }
}
