package bg.sofia.uni.fmi.mjt.csvprocessor.table.column;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseColumnTest {

    private Column column;

    @BeforeEach
    void setUp() {
        column = new BaseColumn();
    }

    @Test
    void testAddDataThrowsExceptionWhenDataIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> column.addData(null),
                "addData() should throw IllegalArgumentException if given data is null!");

        assertThrows(IllegalArgumentException.class, () -> column.addData("   "),
                "addData() should throw IllegalArgumentException if data is blank!");
    }

    @Test
    void testAddData() {
        assertDoesNotThrow(() -> column.addData("testData"),
                "addData() should not throw if called with correct data!");

        assertTrue(column.getData().contains("testData"),
                "Data should be added to the column list of data.");
    }

    @Test
    void testGetData() {
        column.addData("testData1");
        column.addData("testData2");
        column.addData("testData3");

        Collection<String> result = column.getData();

        assertFalse(result.isEmpty(),
                "Collection returned from getData() shouldn't be empty after additions!");

        Collection<String> testSet = new LinkedHashSet<>();
        testSet.add("testData1");
        testSet.add("testData2");
        testSet.add("testData3");

        assertIterableEquals(testSet, result,
                "getData() should return correct collection!");

        assertThrows(UnsupportedOperationException.class, () -> result.add("testData4"),
                "getData() should return an unmodifiable collection!");
    }

}
