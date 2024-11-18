package project.users.exceptions;

public class InvalidEmailException extends Exception {

    //CONSTRUCTOR
    public InvalidEmailException() {}

    //ERROR
    public void messageInvalidEmailException(){
        System.out.println("INVALID EMAIL");
        System.out.println("please enter a new email: ");
    }
}
