package com.phos.email.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created by Folarin on 16/08/2020
 */
public class EmailModel {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String message;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

}
