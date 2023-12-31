package bg.sofia.uni.fmi.mjt.photoalbum.image;

import java.awt.image.BufferedImage;

public class Image {

    private String name;
    private BufferedImage data;

    public Image(String name, BufferedImage data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getData() {
        return data;
    }

    public String getFormat() {
        int lastDotIndex = name.lastIndexOf('.');

        if (lastDotIndex != -1 && lastDotIndex < name.length() - 1) {
            return name.substring(lastDotIndex + 1).toLowerCase();
        }

        return "png";
    }

}
