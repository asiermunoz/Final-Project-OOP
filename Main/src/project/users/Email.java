package project.users;

import project.users.exceptions.InvalidEmailException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email{
    private String email;

    //CONSTRUCTOR
    public Email(String email) {
        this.email = email;
    }

    //GETTER Y SETTER
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    //METODOS
    public void validateEmail() throws InvalidEmailException {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(this.email);
        if(! matcher.matches()){
            throw new InvalidEmailException();
        }
    }

}
