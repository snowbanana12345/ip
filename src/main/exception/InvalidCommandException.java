package main.exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String s) {
        super(s);
    }
}
