package libraryManagementSystem.ISBN;

// TODO: TEST THE ERROR MESSAGES
// TODO: FINISH THE METHODS
// TODO: TEST THE METHODS

public class ISBNHandler {
    private String value;
    private ISBNVersion format;

    public ISBNHandler(String value, ISBNVersion format) {
        // Format the input so there are no dashes or whitespaces.
        this.value = value.replace("-", "").replace(" ", "").strip();
        this.format = format;
    }

    public boolean isValid() {
        return verifyValue(value) && verifyLengthFormatPair(value.length(), format);
    }

    public boolean verifyValue(String value) {
        if (value.length() == 10) {
            return verifyValueOfTen(value);
        }

        if (value.length() == 13) {
            return verifyValueOfThirteen(value);
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
        throw new UnsupportedOperationException("Implement the method");
    }

    public boolean verifyLengthFormatPair(int length, ISBNVersion format) {
        if (length == 10 && !format.equals(ISBNVersion.TEN_DIGIT)) {
            return false;
        }

        return length != 13 || format.equals(ISBNVersion.THIRTEEN_DIGIT);
    }

    public void tenDigitToThirteenDigit(String value) {
        throw new UnsupportedOperationException("Implement the method");
    }
}
