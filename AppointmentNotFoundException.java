package dentalappt.exception;

public class AppointmentNotFoundException extends RuntimeException  {
	public AppointmentNotFoundException() {
        super();
    }

    public AppointmentNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppointmentNotFoundException(final String message) {
        super(message);
    }

    public AppointmentNotFoundException(final Throwable cause) {
        super(cause);
    }
}
