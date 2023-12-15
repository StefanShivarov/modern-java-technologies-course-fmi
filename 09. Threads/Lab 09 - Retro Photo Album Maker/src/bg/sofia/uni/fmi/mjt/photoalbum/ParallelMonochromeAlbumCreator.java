package bg.sofia.uni.fmi.mjt.photoalbum;

import bg.sofia.uni.fmi.mjt.photoalbum.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class ParallelMonochromeAlbumCreator implements MonochromeAlbumCreator {

    public ParallelMonochromeAlbumCreator(int imageProcessorsCount) {

    }

    @Override
    public void processImages(String sourceDirectory, String outputDirectory) {

    }

    public Image loadImage(Path imagePath) {
        try {
            BufferedImage imageData = ImageIO.read(imagePath.toFile());
            return new Image(imagePath.getFileName().toString(), imageData);
        } catch (IOException e) {
            throw new UncheckedIOException(String.format("Failed to load image %s", imagePath.toString()), e);
        }
    }

    private Image convertToBlackAndWhite(Image image) {
        BufferedImage processedData = new BufferedImage(image.getData().getWidth(), image.getData().getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        processedData.getGraphics().drawImage(image.getData(), 0, 0, null);

        return new Image(image.getName(), processedData);
    }

}
