package main.duke_exception;

public class InvalidFieldException extends Exception{
    public InvalidFieldException(String message){
        super(message);
    }
}
