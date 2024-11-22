package ucab.edu.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String email;
    private String alias;

    public User(String email, String alias) {
        this.email = email;
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
