import bg.sofia.uni.fmi.mjt.photoalbum.MonochromeAlbumCreator;
import bg.sofia.uni.fmi.mjt.photoalbum.ParallelMonochromeAlbumCreator;

public class Main {

    public static void main(String[] args) {
        MonochromeAlbumCreator albumCreator = new ParallelMonochromeAlbumCreator(5);
        long startTime = System.currentTimeMillis();
        albumCreator.processImages("resources/original", "resources/converted");
        System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
    }

}
