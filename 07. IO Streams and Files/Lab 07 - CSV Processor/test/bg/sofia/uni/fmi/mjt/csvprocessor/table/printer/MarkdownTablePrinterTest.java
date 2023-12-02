package bg.sofia.uni.fmi.mjt.csvprocessor.table.printer;

import bg.sofia.uni.fmi.mjt.csvprocessor.exceptions.CsvDataNotCorrectException;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.BaseTable;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class MarkdownTablePrinterTest {

    private TablePrinter tablePrinter;

    @BeforeEach
    void setUp() {
        tablePrinter = new MarkdownTablePrinter();
    }

    @Test
    void testPrintTable() throws CsvDataNotCorrectException {
        Table table = new BaseTable();
        table.addData(new String[]{"col1", "col2", "col3", "col4"});
        table.addData(new String[]{"data1", "data2", "data3", "data4"});
        table.addData(new String[]{"data5", "data6", "data7", "data8"});

        Collection<String> result = tablePrinter.printTable(
                table,
                ColumnAlignment.LEFT,
                ColumnAlignment.CENTER,
                ColumnAlignment.RIGHT
        );

        Collection<String> expected = new ArrayList<>();
        expected.add("| col1  | col2  | col3  | col4  |");
        expected.add("| :---- | :---: | ----: | ----- |");
        expected.add("| data1 | data2 | data3 | data4 |");
        expected.add("| data5 | data6 | data7 | data8 |");

        assertIterableEquals(expected, result,
                "printTable() should return correct collection of table rows in markdown syntax!");
    }

}
