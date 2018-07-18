package de.seven.fate.model.builder.constants;

public final class Constants {

    public static final String MAY_NOT_BE_NULL = "%s may not be null";
    public static final String MAY_NOT_BE_EMPTY = "%s may not be empty";

    private Constants() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }
}
