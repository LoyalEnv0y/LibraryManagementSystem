package libraryManagementSystem.ISBN;

public class ISBN {
    private String value;
    private ISBNVersion version;
    private final ISBNHandler handler;
    private boolean isValid;

    public ISBN(String value, ISBNVersion format) {
        this.handler = new ISBNHandler(value, format);
        this.value = formatValue(value);
        this.version = format;
        this.isValid = handler.isValid();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String formattedValue = formatValue(value);

        if (!handler.verifyValue(formattedValue)) {
            this.isValid = false;
        }

        this.value = formattedValue;
    }

    public ISBNVersion getVersion() {
        return version;
    }

    // TODO: TEST THE METHOD AND ERROR MESSAGE
    public void setVersion(ISBNVersion version) {
        if (!handler.verifyLengthFormatPair(value.length(), version)) {
            throw new IllegalArgumentException("\nERROR:\n  Incompatible length");
        }

        this.version = version;
    }

    public String formatValue(String value) {
        return value.replace("-", "").replace(" ", "").strip();
    }

    public boolean isValid() {
        return this.isValid;
    }
}
