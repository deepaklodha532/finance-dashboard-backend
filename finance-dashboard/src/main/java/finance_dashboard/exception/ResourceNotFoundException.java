package finance_dashboard.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not found Exception ") ;
    }

    public ResourceNotFoundException(String message){
        super(message) ;
    }
}
