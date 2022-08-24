package libraryManagementSystem.ISBN;

public class ISBNHandler {
    public boolean verifyValue(String value) {
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

    // TODO: FINISH THE METHOD AND WRITE TESTS
    private boolean verifyValueOfThirteen(String value) {
        throw new UnsupportedOperationException("Implement the method");
    }

    public boolean verifyVersion(int length, ISBNVersion format) {
        if (length == 10 && format.equals(ISBNVersion.TEN_DIGIT)) {
            return true;
        }

        return length == 13 && format.equals(ISBNVersion.THIRTEEN_DIGIT);
    }

    // TODO: FINISH THE METHOD AND WRITE TESTS
    public void tenDigitToThirteenDigit(String value) {
        throw new UnsupportedOperationException("Implement the method");
    }

    public String formatValue(String value) {
        return value.replace("-", "").replace(" ", "").strip();
    }
}
