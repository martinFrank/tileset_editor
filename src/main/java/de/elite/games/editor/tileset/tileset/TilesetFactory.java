package de.elite.games.editor.tileset.tileset;

import de.elite.games.editor.tileset.TilesetController;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TilesetFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TilesetFactory.class);

    public Tileset fromFile(File f) throws IOException {
        Path path = f.toPath();
        String name = f.getName();
        String id = f.getName();
        byte[] bytes = Files.readAllBytes(path);
        LOGGER.debug("file {}", f);
        Image image = new Image(new FileInputStream(f));
        return new Tileset(name, id, bytes, image);

    }
}
