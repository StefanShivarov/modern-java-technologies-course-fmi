package bg.sofia.uni.fmi.mjt.photoalbum;

import bg.sofia.uni.fmi.mjt.photoalbum.image.Image;
import bg.sofia.uni.fmi.mjt.photoalbum.image.threads.ImageConsumer;
import bg.sofia.uni.fmi.mjt.photoalbum.image.threads.ImageProducer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        createDirectoriesIfNecessary(outputPath);

        AtomicBoolean allImagesLoaded = new AtomicBoolean(false);
        AtomicInteger loadedImagesAmount = new AtomicInteger(0);

        List<Thread> producerThreads = new ArrayList<>();
        try {
            List<Path> imagePaths = getImagePaths(sourcePath);
            AtomicInteger imagesAmount = new AtomicInteger(imagePaths.size());
            imagePaths.forEach(path -> {
                Thread producer = new Thread(
                        new ImageProducer(path, images, loadedImagesAmount, imagesAmount, allImagesLoaded)
                );
                producerThreads.add(producer);
                producer.start();
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to get image paths! " + e.getMessage(), e);
        }

        List<Thread> consumerThreads = new ArrayList<>(imageProcessorsCount);
        for (int i = 0; i < imageProcessorsCount; i++) {
            Thread consumer = new Thread(
                    new ImageConsumer(outputDirectory, images, allImagesLoaded)
            );
            consumer.setName("Consumer-" + i);
            consumerThreads.add(i, consumer);
            consumer.start();
        }

        joinThreads(producerThreads);
        joinThreads(consumerThreads);
    }

    private void createDirectoriesIfNecessary(Path outputPath) {
        try {
            if (!Files.exists(outputPath)) {
                Files.createDirectories(outputPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while creating directories! " + e.getMessage(), e);
        }
    }

    public static List<Path> getImagePaths(Path sourcePath) throws IOException {
        try (Stream<Path> paths = Files.walk(sourcePath)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".jpg")
                            || path.toString().toLowerCase().endsWith(".jpeg")
                            || path.toString().toLowerCase().endsWith(".png"))
                    .collect(Collectors.toList());
        }
    }

    private void joinThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(thread.getName() + " joined!");
        }
    }

}
