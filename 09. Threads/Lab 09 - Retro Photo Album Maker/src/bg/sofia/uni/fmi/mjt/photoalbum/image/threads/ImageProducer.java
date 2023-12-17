package bg.sofia.uni.fmi.mjt.photoalbum.image.threads;

import bg.sofia.uni.fmi.mjt.photoalbum.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageProducer implements Runnable {

    private final Path imagePath;
    private final Queue<Image> images;
    private final AtomicInteger loadedImagesAmount;
    private final AtomicInteger imagesAmount;
    private final AtomicBoolean allImagesLoaded;

    public ImageProducer(Path imagePath, Queue<Image> images, AtomicInteger loadedImagesAmount,
                         AtomicInteger imagesAmount, AtomicBoolean allImagesLoaded) {
        this.imagePath = imagePath;
        this.images = images;
        this.loadedImagesAmount = loadedImagesAmount;
        this.imagesAmount = imagesAmount;
        this.allImagesLoaded = allImagesLoaded;
    }

    @Override
    public void run() {
        Image loadedImage = loadImage();
        synchronized (images) {
            images.offer(loadedImage);
            System.out.println(Thread.currentThread().getName() + " loaded " + loadedImage.getName());
            if (loadedImagesAmount.incrementAndGet() == imagesAmount.get()) {
                allImagesLoaded.set(true);
            }

            images.notifyAll();
        }
    }

    public Image loadImage() {
        try {
            BufferedImage imageData = ImageIO.read(imagePath.toFile());
            return new Image(imagePath.getFileName().toString(), imageData);
        } catch (IOException e) {
            throw new UncheckedIOException(String.format("Failed to load image %s", imagePath.toString()), e);
        }
    }

}
