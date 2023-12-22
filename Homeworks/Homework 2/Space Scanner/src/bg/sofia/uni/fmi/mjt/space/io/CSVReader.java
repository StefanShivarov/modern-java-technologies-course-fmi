package bg.sofia.uni.fmi.mjt.space.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements AutoCloseable {

    private final BufferedReader reader;

    public CSVReader(Reader reader) {
        this.reader = new BufferedReader(reader);
    }

    public List<String[]> readAllLines() throws IOException {
        List<String[]> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",(?=(?:(?:[^\"]*\"){2})*[^\"]*$)");
            lines.add(values);
        }

        return lines;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

}
