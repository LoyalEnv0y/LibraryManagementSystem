package libraryManagementSystem.book;

public class BookISBN {
    private String value;

    public BookISBN(String value) {
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        verifyIsbn(value);
        this.value = value;
    }

    public void verifyIsbn(String value) {
        String formattedValue = formatValue(value);

        if (!hasValidValue(formattedValue)) {
            throw new IllegalArgumentException(
                    "\nERROR:\n  Value -> "
                            + value + " is not a valid ISBN value"
            );
        }
    }

    public void verifyIsbn() {
        verifyIsbn(this.getValue());
    }

    public boolean hasValidValue(String value) {
        String formattedValue = formatValue(value);

        if (formattedValue.length() == 10) {
            return verifyValueOfTen(formattedValue);
        }

        if (formattedValue.length() == 13) {
            return verifyValueOfThirteen(formattedValue);
        }

        return false;
    }

    private boolean verifyValueOfTen(String value) {
        int total = 0;
        String firstNine = value.substring(0, 9);

        // Validate that the first 9 characters contain only digits.
        if (!firstNine.matches("[0-9]{9}")) {
            return false;
        }

        // Each of the first nine digits of the ISBN is multiplied by their weight and added to sum
        for (int weight = 10, index = 0; weight >= 2; weight--, index++) {
            total += weight * (value.charAt(index) - '0');
        }

        // Handle the check digit
        char lastDigit = value.charAt(9);
        if (!Character.isDigit(lastDigit) && lastDigit != 'X') {
            return false;
        }

        total += (lastDigit == 'X') ? 10 : lastDigit - '0';
        return total % 11 == 0;
    }

    private boolean verifyValueOfThirteen(String value) {
        int sum = 0;
        boolean multiplyByThree = false;

        // Verify value only contains 13 digits
        if (!value.matches("[0-9]{13}")) {
            return false;
        }

        // Each of the digits of the ISBN is multiplied by either 1 or 3 and added to sum
        for (int i = 0; i < 13; i++) {
            int multiplier = (multiplyByThree) ? 3 : 1;

            sum += (value.charAt(i) - '0') * multiplier;
            multiplyByThree = !multiplyByThree;
        }

        return sum % 10 == 0;
    }

    public String formatValue(String value) {
        return value.replace("-", "").replace(" ", "").strip();
    }
}
