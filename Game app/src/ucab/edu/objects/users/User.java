package ucab.edu.objects.users;

import ucab.edu.objects.users.exceptions.InvalidAliasException;

public class User{
    private String alias;
    private Email email;

    //CONSTRUCTOR
    public User(String alias, Email email1) {
        this.alias = alias;
        this.email = email1;
    }

    //GETTER Y SETTER
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {this.alias = alias;}
    public Email getEmail(){ return email;}
    public String getStringEmail() {
        String mail = email.getEmail();
        return mail;
    }
    public void setEmail(Email email) {this.email = email;}


    //METODOS
    public void validateAlias() throws InvalidAliasException {
        if (this.alias == null || this.alias.isEmpty()) {
            throw new InvalidAliasException();
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        User user = (User) object;
        return alias.equals(user.alias) && email.equals(user.email);
    }

    public boolean equalsName(String name) {
        if (this.alias.equals(name)) {
            return true;
        }
        return false;
    }
}
