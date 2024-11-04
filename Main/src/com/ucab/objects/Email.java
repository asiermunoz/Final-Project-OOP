package com.ucab.objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validateEmail() {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

}
