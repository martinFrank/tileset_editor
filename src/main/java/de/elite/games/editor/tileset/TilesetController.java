package de.elite.games.editor.tileset;

import de.elite.games.editor.tileset.tileset.Tileset;
import de.elite.games.editor.tileset.tileset.TilesetFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TilesetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TilesetController.class);

    private final TilesetFactory tilesetFactory = new TilesetFactory();
    private Tileset selected;
    private File lastVisitedDirectory = new File(".");

    @FXML
    private ListView<Tileset> tilesetList;

    @FXML
    private Button addTilesetButton;

    @FXML
    private TextField tilesetNameText;
    @FXML
    private TextField tilesetIdText;
    @FXML
    private TextField tilesetWidthText;
    @FXML
    private TextField tilesetHeightText;

    @FXML
    private ImageView tilesetImageView;
    @FXML
    private Pane tilesetImagePane;


    @FXML
    protected void handleAddTilesetButtonAction(ActionEvent event) {
        Window owner = addTilesetButton.getScene().getWindow();
        LOGGER.debug("add tileset button pressed ");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Tileset Image File");
        fileChooser.setInitialDirectory(lastVisitedDirectory);
        File file = fileChooser.showOpenDialog(owner);
        if (file != null) {
            LOGGER.debug("opened file: {}", file);
            lastVisitedDirectory = file.getParentFile();
            try {
                Tileset tileset = tilesetFactory.fromFile(file);
                selectTileset(tileset);
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
        tilesetList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectTileset(newValue));
    }

    private void selectTileset(Tileset selected) {
        this.selected = selected;
        tilesetNameText.setText(selected.getName());
        tilesetIdText.setText(selected.getId());
        tilesetWidthText.setText("" + selected.getWidth());
        tilesetHeightText.setText("" + selected.getHeight());
        tilesetImageView.setImage(selected.getImage());
        tilesetImageView.setFitHeight(selected.getImageHeight());
        tilesetImageView.setFitWidth(selected.getImageWidth());
        tilesetImagePane.getChildren().clear();
        tilesetImagePane.getChildren().add(tilesetImageView);
        List<Rectangle> rectangles = createRectangles();
        tilesetImagePane.getChildren().addAll(rectangles);

    }

    private List<Rectangle> createRectangles() {
        List<Rectangle> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle rectangle = new Rectangle(i * 32, 0, 32, 32);
            rectangle.setFill(null);
            rectangle.setStroke(Color.GREEN);
            list.add(rectangle);
        }
        return list;
    }
}
