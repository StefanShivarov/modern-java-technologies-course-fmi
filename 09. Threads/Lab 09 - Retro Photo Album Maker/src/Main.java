import bg.sofia.uni.fmi.mjt.photoalbum.MonochromeAlbumCreator;
import bg.sofia.uni.fmi.mjt.photoalbum.ParallelMonochromeAlbumCreator;

public class Main {

    public static void main(String[] args) {
        MonochromeAlbumCreator albumCreator = new ParallelMonochromeAlbumCreator(5);
        long startTime = System.currentTimeMillis();
        albumCreator.processImages("resources/original", "resources/converted");

        /*
           Sleeping the thread for 1000 milliseconds to make sure it prints out the execution time
           after the method has finished completely
         */

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Execution time: " + (System.currentTimeMillis() - startTime - 1000));
    }

}
