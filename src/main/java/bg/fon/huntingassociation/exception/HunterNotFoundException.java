package bg.fon.huntingassociation.exception;

public class HunterNotFoundException extends RuntimeException{

    public HunterNotFoundException(String message){
        super(message);
    }
}
