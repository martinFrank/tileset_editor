package de.elite.games.editor.tileset.tileset;

import javafx.scene.image.Image;

public class Tileset {

    private byte[] imageBytes;
    private String name;
    private String id;
    private int width;
    private int height;
    private transient Image image;

    public Tileset(String name, String id, byte[] bytes, Image image) {
        this.name = name;
        this.id = id;
        this.imageBytes = bytes;
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
