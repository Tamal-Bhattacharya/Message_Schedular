package com.bhattacharya.entities;

public class Account_Credential {
    private int Account_ID;
    private String userName;
    private String password;

    public int getAccount_ID() {
        return Account_ID;
    }
    public void setAccount_ID(int account_ID) {
        Account_ID = account_ID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
