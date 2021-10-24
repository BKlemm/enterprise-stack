package com.avondock.app.system.iam.auth.request;


import lombok.Data;

@Data
public class AuthenticationData {

    String userName;

    String password;
}
