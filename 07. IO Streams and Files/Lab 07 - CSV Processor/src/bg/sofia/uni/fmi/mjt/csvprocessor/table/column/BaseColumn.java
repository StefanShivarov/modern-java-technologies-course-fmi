package bg.sofia.uni.fmi.mjt.csvprocessor.table.column;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseColumn implements Column {

    private Set<String> values;
    private static final int MIN_LENGTH = 3;
    private int maxLength;

    public BaseColumn() {
        this(new LinkedHashSet<>());
        this.maxLength = MIN_LENGTH;
    }

    public BaseColumn(Set<String> values) {
        this.values = values;
    }

    @Override
    public void addData(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Data is null or blank!");
        }

        if (data.length() > maxLength) {
            maxLength = data.length();
        }

        values.add(data);
    }

    @Override
    public Collection<String> getData() {
        return Collections.unmodifiableSet(values);
    }

    public int getMaxLength() {
        return maxLength;
    }

}
