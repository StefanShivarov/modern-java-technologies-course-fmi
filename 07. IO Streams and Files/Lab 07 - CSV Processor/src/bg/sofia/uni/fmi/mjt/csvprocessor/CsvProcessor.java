package bg.sofia.uni.fmi.mjt.csvprocessor;

import bg.sofia.uni.fmi.mjt.csvprocessor.exceptions.CsvDataNotCorrectException;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.BaseTable;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.Table;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.printer.ColumnAlignment;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.printer.MarkdownTablePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

public class CsvProcessor implements CsvProcessorAPI {

    private Table table;

    public CsvProcessor() {
        this(new BaseTable());
    }

    public CsvProcessor(Table table) {
        this.table = table;
    }

    @Override
    public void readCsv(Reader reader, String delimiter) throws CsvDataNotCorrectException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader is null!");
        }

        if (delimiter.isBlank()) {
            throw new IllegalArgumentException("Delimiter is blank!");
        }

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {

            String[] headers = bufferedReader.readLine().split("\\Q" + delimiter + "\\E");
            table.addData(headers);

            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] data = nextLine.split("\\Q" + delimiter + "\\E");

                if (data.length != headers.length) {
                    throw new CsvDataNotCorrectException("Columns amount is different to the headers provided!");
                }

                table.addData(data);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeTable(Writer writer, ColumnAlignment... alignments) {
        if (writer == null) {
            throw new IllegalArgumentException("Writer is null!");
        }

        Collection<String> tableRows = new MarkdownTablePrinter().printTable(table, alignments);

        try{
            writer.write(String.join(System.lineSeparator(), tableRows));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //... do something else
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                //... do something else
            }
        }
    }
}
