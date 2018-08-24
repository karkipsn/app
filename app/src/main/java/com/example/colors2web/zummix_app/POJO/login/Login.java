package com.example.colors2web.zummix_app.POJO.login;

public class Login {

         String email;

         String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
