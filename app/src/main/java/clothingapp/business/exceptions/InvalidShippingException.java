package clothingapp.business.exceptions;

public class InvalidShippingException extends Exception {
    public InvalidShippingException(String message) {
        super(message);
    }
}