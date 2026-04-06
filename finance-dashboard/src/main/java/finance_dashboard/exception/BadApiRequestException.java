package finance_dashboard.exception;

public class BadApiRequestException extends  RuntimeException {

    public BadApiRequestException(){
        super("BAD API REQUEST") ;
    }

    public BadApiRequestException(String message){
        super(message) ;
    }
}
