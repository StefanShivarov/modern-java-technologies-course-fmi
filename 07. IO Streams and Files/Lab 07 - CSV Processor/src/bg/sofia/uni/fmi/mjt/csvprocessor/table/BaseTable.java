package bg.sofia.uni.fmi.mjt.csvprocessor.table;

import bg.sofia.uni.fmi.mjt.csvprocessor.exceptions.CsvDataNotCorrectException;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.column.BaseColumn;
import bg.sofia.uni.fmi.mjt.csvprocessor.table.column.Column;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseTable implements Table {

    private Map<String, Column> tableColumns;
    private int rowsCount;

    public BaseTable() {
        this.tableColumns = new LinkedHashMap<>();
    }

    @Override
    public void addData(String[] data) throws CsvDataNotCorrectException {
        if (data == null) {
            throw new IllegalArgumentException("Data is null!");
        }

        if (!tableColumns.isEmpty() && data.length != tableColumns.size()) {
            throw new CsvDataNotCorrectException("CSV data provided doesn't have same amount of columns!");
        }

        if (tableColumns.isEmpty()) {
            for (String s : data) {
                tableColumns.put(s, new BaseColumn());
            }
        } else {
            int dataIndex = 0;
            for (Column column : tableColumns.values()) {
                column.addData(data[dataIndex++]);
            }
        }

        rowsCount++;
    }

    @Override
    public Collection<String> getColumnNames() {
        return Collections.unmodifiableSet(tableColumns.keySet());
    }

    @Override
    public Collection<String> getColumnData(String column) {
        return tableColumns.get(column).getData();
    }

    @Override
    public int getRowsCount() {
        return rowsCount;
    }

}
