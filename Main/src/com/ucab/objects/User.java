package com.ucab.objects;

import com.ucab.objects.exceptions.InvalidAliasException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    String alias;
    Email email;

    public User(String alias, Email email) {
        this.alias = alias;
        this.email = email;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void validateAlias() throws InvalidAliasException {
        if (this.alias == null || this.alias.isEmpty()) {
            throw new InvalidAliasException();
        }
    }
}
