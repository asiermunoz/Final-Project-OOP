package ucab.edu.objects.users.exceptions;

public class InvalidAliasException extends Exception {

    //CONSTRUCTOR
    public InvalidAliasException() {}

    //ERROR
    public void messageInvalidAliasException(){
        System.out.println("INVALID ALIAS");
        System.out.println("please enter a new alias: ");
    }
}
