package de.microtema.model.builder.constants;

public final class Constants {

    public static final String MAY_NOT_BE_NULL = "%s may not be null";
    public static final String MAY_NOT_BE_EMPTY = "%s may not be empty";

    public static final int MIN_COLLECTION_SIZE = 1;
    public static final int MAX_COLLECTION_SIZE = 10;

    private Constants() {
        throw new UnsupportedOperationException(getClass().getName() + " should not be called with new!");
    }
}
