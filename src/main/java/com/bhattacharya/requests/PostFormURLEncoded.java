package com.bhattacharya.requests;

public class PostFormURLEncoded {
    private String userName;
    private String password;
    private String msg;
    private String time;
    private String sendTo;

    public PostFormURLEncoded() {
    }

    public PostFormURLEncoded(String userName, String password, String msg, String time, String sendTo) {
        this.userName = userName;
        this.password = password;
        this.msg = msg;
        this.time = time;
        this.sendTo = sendTo;
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
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getSendTo() {
        return sendTo;
    }
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
    @Override
    public String toString() {
        return "PostFormURLEncoded [msg=" + msg + ", password=" + password + ", sendTo=" + sendTo + ", time=" + time
                + ", userName=" + userName + "]";
    }

    
}
