package bg.sofia.uni.fmi.mjt.csvprocessor.table;

import bg.sofia.uni.fmi.mjt.csvprocessor.exceptions.CsvDataNotCorrectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseTableTest {

    private Table table;

    @BeforeEach
    void setUp() {
        table = new BaseTable();
    }

    @Test
    void testAddDataThrowsExceptionWhenDataIsNull() {
        assertThrows(IllegalArgumentException.class, () -> table.addData(null),
                "addData() should throw IllegalArgumentException if data is null!");
    }

    @Test
    void testAddDataThrowsExceptionForInvalidColumnsAmount() throws CsvDataNotCorrectException {
        table.addData(new String[]{"col1", "col2", "col3"});
        assertThrows(CsvDataNotCorrectException.class,
                () -> table.addData(new String[]{"data1", "data2"}),
                "addData() should throw CsvDataNotCorrectException if columns don't match!");
    }

    @Test
    void testGetRowsCount() throws CsvDataNotCorrectException {
        assertEquals(0, table.getRowsCount(),
                "Row count should be 0 if no data was added!");

        table.addData(new String[]{"row1_1", "row1_2"});
        table.addData(new String[]{"row2_1", "row2_2"});
        table.addData(new String[]{"row3_1", "row3_2"});

        assertEquals(3, table.getRowsCount(),
                "getRowsCount() should return correct rows count!");
    }

    @Test
    void testGetColumnNames() throws CsvDataNotCorrectException {
        table.addData(new String[]{"col1", "col2", "col3"});
        table.addData(new String[]{"data1", "data2", "data3"});
        Collection<String> result = table.getColumnNames();

        Collection<String> expected = new LinkedHashSet<>();
        expected.add("col1");
        expected.add("col2");
        expected.add("col3");

        assertIterableEquals(expected, result,
                "getColumnNames() should return correct column names!");

        assertThrows(UnsupportedOperationException.class, () -> result.add("col4"),
                "getColumnNames() should return an unmodifiable collection!");
    }

    @Test
    void testGetColumnDataThrowsExceptionIfColumnIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> table.getColumnData(null),
                "getColumnData() should return IllegalArgumentException if column is null!");

        assertThrows(IllegalArgumentException.class, () -> table.getColumnData("   "),
                "getColumnData() should return IllegalArgumentException if column is blank!");
    }

    @Test
    void testGetColumnDataThrowsExceptionIfColumnDoesntExist(){
        assertThrows(IllegalArgumentException.class, () -> table.getColumnData("col5"),
                "getColumnData() should return IllegalArgumentException if column doesn't exist!");
    }

    @Test
    void testGetColumnData() throws CsvDataNotCorrectException {
        table.addData(new String[]{"col1", "col2", "col3"});
        table.addData(new String[]{"data1", "data2", "data3"});
        table.addData(new String[]{"data4", "data5", "data6"});

        Collection<String> secondColumnResult = table.getColumnData("col2");
        Collection<String> expected = new LinkedHashSet<>();
        expected.add("data2");
        expected.add("data5");

        assertIterableEquals(expected, secondColumnResult,
                "getColumnData() should return correct column data!");
    }

}
