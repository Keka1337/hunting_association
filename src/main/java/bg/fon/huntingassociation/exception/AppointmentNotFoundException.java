package bg.fon.huntingassociation.exception;

public class AppointmentNotFoundException extends  RuntimeException{
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
