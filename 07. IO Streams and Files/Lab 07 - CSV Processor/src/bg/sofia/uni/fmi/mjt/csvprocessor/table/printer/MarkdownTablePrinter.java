package bg.sofia.uni.fmi.mjt.csvprocessor.table.printer;

import bg.sofia.uni.fmi.mjt.csvprocessor.table.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MarkdownTablePrinter implements TablePrinter {

    private static final int MIN_CELL_LENGTH = 3;

    @Override
    public Collection<String> printTable(Table table, ColumnAlignment... alignments) {
        List<String> tableRows = new ArrayList<>();

        StringBuilder headerRowBuilder = new StringBuilder().append('|'),
                alignmentsRowBuilder = new StringBuilder().append('|');

        int columnsAmount = table.getColumnNames().size(), alignmentsIndex = 0;
        for (String header : table.getColumnNames()) {
            int maxLengthForColumn = getMaxLengthForColumn(table, header);
            headerRowBuilder
                    .append(formatCell(header, getMaxLengthForColumn(table, header)))
                    .append('|');

            ColumnAlignment alignment = ColumnAlignment.NOALIGNMENT;
            if (alignmentsIndex < columnsAmount && alignmentsIndex < alignments.length) {
                alignment = alignments[alignmentsIndex++];
            }
            alignmentsRowBuilder.append(
                    formatAlignmentCell(alignment, maxLengthForColumn)
            ).append('|');
        }

        tableRows.add(headerRowBuilder.toString());
        tableRows.add(alignmentsRowBuilder.toString());

        for (int i = 0; i < table.getRowsCount() - 1; i++) {
            StringBuilder rowBuilder = new StringBuilder().append('|');

            for (String header : table.getColumnNames()) {
                String data = (String) table.getColumnData(header).toArray()[i];
                rowBuilder
                        .append(formatCell(data, getMaxLengthForColumn(table, header)))
                        .append('|');
            }

            tableRows.add(rowBuilder.toString());
        }

        return Collections.unmodifiableList(tableRows);
    }

    private String formatCell(String data, int maxLength) {
        int spacesToAdd = maxLength - data.length();
        return " " + data + " ".repeat(spacesToAdd + 1);
    }

    private String formatAlignmentCell(ColumnAlignment alignment, int maxLength) {
        String dashes = "-".repeat(maxLength - alignment.getAlignmentCharactersCount());

        return switch (alignment) {
            case LEFT -> String.format(" :%s ", dashes);
            case CENTER -> String.format(" :%s: ", dashes);
            case RIGHT -> String.format(" %s: ", dashes);
            case NOALIGNMENT -> String.format(" %s ", dashes);
        };
    }

    private int getMaxLengthForColumn(Table table, String columnName) {
        int maxLength = Math.max(columnName.length(), MIN_CELL_LENGTH);
        for (String s : table.getColumnData(columnName)) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }

        return maxLength;
    }

}
