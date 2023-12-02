package bg.sofia.uni.fmi.mjt.csvprocessor;

import bg.sofia.uni.fmi.mjt.csvprocessor.exceptions.CsvDataNotCorrectException;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.Table;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.printer.ColumnAlignment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CsvProcessorTest {

    @Mock
    private Table tableMock;

    @InjectMocks
    private static CsvProcessor csvProcessor;

    @BeforeAll
    static void setUp() {
        csvProcessor = new CsvProcessor();
    }

    @Test
    void testReadCsvThrowsExceptionIfReaderIsNullOrDelimiterIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> csvProcessor.readCsv(null, ","),
                "readCsv() should throw IllegalArgumentException if reader is null!");
        assertThrows(IllegalArgumentException.class, () -> csvProcessor.readCsv(new StringReader("sd"), "  "),
                "readCsv() should throw IllegalArgumentException if delimiter is blank!");
    }

    @Test
    void testReadCsvWithIncorrectDataThrowsException() throws CsvDataNotCorrectException {
        String incorrectCsvContent = "Name,Age\nStefan,20,Bulgaria";
        StringReader stringReader = new StringReader(incorrectCsvContent);

        assertThrows(CsvDataNotCorrectException.class, () ->
                        csvProcessor.readCsv(stringReader, ","),
                "readCsv() should throw CsvDataNotCorrectException if csv data is invalid!");
    }

    @Test
    void testReadCsv() throws CsvDataNotCorrectException {
        String csvContent = "Name,Age,Nationality\nStefan,20,Bulgaria\nMary,19,England";
        StringReader stringReader = new StringReader(csvContent);
        try {
            csvProcessor.readCsv(stringReader, ",");
        } catch (CsvDataNotCorrectException e) {
            fail("Unexpected exception was thrown!");
        }

        verify(tableMock).addData(new String[]{"Name", "Age", "Nationality"});
        verify(tableMock).addData(new String[]{"Stefan", "20", "Bulgaria"});
        verify(tableMock).addData(new String[]{"Mary", "19", "England"});
    }

    @Test
    void testWriteTableThrowsExceptionIfWriterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> csvProcessor.writeTable(null),
                "writeTable() should throw IllegalArgumentException if writer is null!");
    }

    @Test
    void testWriteTable() {
        when(tableMock.getRowsCount()).thenReturn(3);
        when(tableMock.getColumnNames())
                .thenReturn(List.of("Name", "Age", "Nationality"));
        when(tableMock.getColumnData("Name")).thenReturn(List.of("Stefan", "Mary"));
        when(tableMock.getColumnData("Age")).thenReturn(List.of("20", "19"));
        when(tableMock.getColumnData("Nationality")).thenReturn(List.of("Bulgaria", "England"));

        StringWriter stringWriter = new StringWriter();


        csvProcessor.writeTable(
                stringWriter,
                ColumnAlignment.LEFT,
                ColumnAlignment.RIGHT,
                ColumnAlignment.CENTER
        );

        String expectedOutput =
                "| Name   | Age | Nationality |" + System.lineSeparator() +
                "| :----- | --: | :---------: |" + System.lineSeparator() +
                "| Stefan | 20  | Bulgaria    |" + System.lineSeparator() +
                "| Mary   | 19  | England     |";

        assertEquals(expectedOutput, stringWriter.toString().trim(),
                "Incorrect table output!");
    }

}
