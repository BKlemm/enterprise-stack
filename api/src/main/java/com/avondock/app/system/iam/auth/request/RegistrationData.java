package com.avondock.app.system.iam.auth.request;

import lombok.Data;

@Data
public class RegistrationData {

    String firstName;
    String lastName;
    String username;
    String password;
}
