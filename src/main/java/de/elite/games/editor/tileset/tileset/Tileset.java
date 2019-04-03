package de.elite.games.editor.tileset.tileset;

import javafx.scene.image.Image;

public class Tileset {

    private byte[] imageBytes;
    private String name;
    private String id;
    private int width;
    private int height;
    private transient Image image;
    private double imageWidth;
    private double imageHeight;

    public Tileset(String name, String id, byte[] bytes, Image image) {
        this.name = name;
        this.id = id;
        this.imageBytes = bytes;
        setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return name;
    }

    private void setImage(Image image) {
        this.image = image;
        imageWidth = image.getWidth();
        imageHeight = image.getHeight();
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }
}
