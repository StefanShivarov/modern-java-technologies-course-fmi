package bg.sofia.uni.fmi.mjt.photoalbum;

import bg.sofia.uni.fmi.mjt.photoalbum.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ParallelMonochromeAlbumCreator implements MonochromeAlbumCreator {

    private final int imageProcessorsCount;
    private Queue<Image> images;

    public ParallelMonochromeAlbumCreator(int imageProcessorsCount) {
        this.imageProcessorsCount = imageProcessorsCount;
        this.images = new ArrayDeque<>();
    }

    @Override
    public void processImages(String sourceDirectory, String outputDirectory) {
        Path sourcePath = Paths.get(sourceDirectory);
        Path outputPath = Paths.get(outputDirectory);

        try {
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Thread> producerThreads = new ArrayList<>();
        try {
            Files.walk(sourcePath)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".jpg")
                            || path.toString().toLowerCase().endsWith(".jpeg")
                            || path.toString().toLowerCase().endsWith(".png"))
                    .forEach(path -> {
                        Thread producer = new Thread(() -> handleLoadedImage(path));
                        producerThreads.add(producer);
                        producer.start();
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Thread> consumerThreads = new ArrayList<>(imageProcessorsCount);
        for (int i = 0; i < imageProcessorsCount; i++) {
            Thread consumer = new Thread(() -> consumeAndProcessImages(outputDirectory));
            consumer.setName("Consumer-" + i);
            consumerThreads.add(i, consumer);
            consumer.start();
        }

        joinThreads(producerThreads);
        notifyConsumersForEndOfImages();
        joinThreads(consumerThreads);
    }

    private void joinThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void notifyConsumersForEndOfImages() {
        synchronized (images) {
            for (int i = 0; i < imageProcessorsCount; i++) {
                images.add(new Image("", null));
            }
            images.notifyAll();
        }
    }

    private void consumeAndProcessImages(String outputDirectory) {
        while (true) {
            Image image;
            synchronized (images) {
                while (images.isEmpty()) {
                    try {
                        images.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                image = images.poll();
                images.notifyAll();
            }

            if (image.getName().isEmpty()) {
                break;
            }

            saveConvertedImage(convertToBlackAndWhite(image), outputDirectory);
        }
    }

    private void handleLoadedImage(Path imagePath) {
        synchronized (images) {
            images.add(loadImage(imagePath));
            images.notifyAll();
        }
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

    private void saveConvertedImage(Image convertedImage, String outputDirectory) {
        File outputDirectoryFile = new File(outputDirectory);

        if (!outputDirectoryFile.exists()) {
            outputDirectoryFile.mkdirs();
        }

        File outputFile = new File(outputDirectory, convertedImage.getName());
        String imageFormat = getImageFormat(convertedImage.getName());

        try {
            ImageIO.write(convertedImage.getData(), imageFormat, outputFile);
        } catch (IOException e) {
            System.out.println("Failed to save image: " + e.getMessage());
        }
    }

    private String getImageFormat(String imageName) {
        int lastDotIndex = imageName.lastIndexOf('.');

        if (lastDotIndex != -1 && lastDotIndex < imageName.length() - 1) {
            return imageName.substring(lastDotIndex + 1).toLowerCase();
        }

        return "png";
    }

}
