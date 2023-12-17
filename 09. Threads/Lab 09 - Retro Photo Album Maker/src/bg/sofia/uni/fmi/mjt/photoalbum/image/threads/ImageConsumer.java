package bg.sofia.uni.fmi.mjt.photoalbum.image.threads;

import bg.sofia.uni.fmi.mjt.photoalbum.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImageConsumer implements Runnable {

    private final String outputDirectory;
    private final Queue<Image> images;
    private final AtomicBoolean allImagesLoaded;

    public ImageConsumer(String outputDirectory, Queue<Image> images, AtomicBoolean allImagesLoaded) {
        this.outputDirectory = outputDirectory;
        this.images = images;
        this.allImagesLoaded = allImagesLoaded;
    }

    @Override
    public void run() {
        while (true) {
            Image nextImage = getNextImage();

            if (nextImage == null) {
                break;
            }

            saveConvertedImage(convertToBlackAndWhite(nextImage));
            System.out.println(Thread.currentThread().getName() + " saved " + nextImage.getName());
        }
    }

    private Image getNextImage() {
        synchronized (images) {
            while (images.isEmpty()) {
                if (allImagesLoaded.get() && images.isEmpty()) {
                    return null;
                }

                try {
                    images.wait();
                    System.out.println(Thread.currentThread().getName() + " is waiting...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }

        return images.poll();
    }

    private Image convertToBlackAndWhite(Image image) {
        BufferedImage processedData = new BufferedImage(image.getData().getWidth(),
                image.getData().getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        processedData.getGraphics().drawImage(image.getData(), 0, 0, null);

        return new Image(image.getName(), processedData);
    }

    private void saveConvertedImage(Image convertedImage) {
        File outputFile = new File(outputDirectory, convertedImage.getName());

        try {
            ImageIO.write(convertedImage.getData(), convertedImage.getFormat(), outputFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
    }

}
