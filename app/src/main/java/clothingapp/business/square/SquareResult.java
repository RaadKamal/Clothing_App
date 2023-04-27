package clothingapp.business.square;

public final class SquareResult {

    public final boolean success;
    public final boolean networkError;
    public final String errorMessage;

    private SquareResult(boolean success, boolean networkError, String errorMessage) {
        this.success = success;
        this.networkError = networkError;
        this.errorMessage = errorMessage;
    }

    public static SquareResult success() {
        return new SquareResult(true, false, null);
    }
    public static SquareResult networkError() { return new SquareResult(false, true, null); }
    public static SquareResult error(String message) { return new SquareResult(false, false, message); }
}
