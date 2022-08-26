package libraryManagementSystem.isbn;

public class ISBN {
    private final ISBNHandler handler;
    private final ISBNVersion version;
    private String value;

    public ISBN(String value, ISBNVersion version) {
        this.handler = new ISBNHandler();
        this.version = version;
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String formattedValue = handler.formatValue(value);

        if (!handler.verifyVersion(formattedValue.length(), version)) {
            throw new IllegalArgumentException(
                    "\nERROR:\n  Incompatible length -> "
                            + value.length() + " is not compatible"
                            + " with " + version
            );
        }

        if (!handler.verifyValue(formattedValue)) {
            throw new IllegalArgumentException(
                    "\nERROR:\n  Value -> "
                            + value + " is not a valid ISBN value"
            );
        }

        this.value = formattedValue;
    }

    public ISBNVersion getVersion() {
        return version;
    }
}
