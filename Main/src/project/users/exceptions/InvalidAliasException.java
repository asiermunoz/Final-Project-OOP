package project.users.exceptions;

public class InvalidAliasException extends RuntimeException {

    //CONSTRUCTOR
    public InvalidAliasException() {}

    //ERROR
    public void messageInvalidAliasException(){
        System.out.println("INVALID ALIAS");
        System.out.println("please enter a new alias: ");
    }
}
