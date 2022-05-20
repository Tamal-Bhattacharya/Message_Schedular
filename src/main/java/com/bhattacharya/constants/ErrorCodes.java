package com.bhattacharya.constants;

public class ErrorCodes {

    // public static final String NO_ERROR = "No_Error";

    public static final String WORNG_ERROR_CODE = "Internal Error";

    public static final String INVALID_USERNAME = "Invalid Username";

    public static final String INVALID_PASSWORD = "Invalid Password";

    public static final String INVALID_MSG = "Message is Empty or Invalid";

    public static final String INVALID_TIME = "Wrong/Invalid time";
    
    public static final String INVALID_SENDTO = "Invalid Phone Number";

    public static final String WRONG_CREDENTIALS = "Mismatched Username and Password";

    public static String codeToString(int errorCode) {
        String value;
        switch (errorCode) {
            case 411:
                value = INVALID_USERNAME;
                break;
            case 412:
                value = INVALID_PASSWORD;
                break;
            case 413:
                value = INVALID_MSG;
                break;
            case 414:
                value = INVALID_TIME;
                break;
            case 415:
                value = INVALID_SENDTO;
                break;
            case 511:
                value = WRONG_CREDENTIALS;
                break;
            default:
                value = WORNG_ERROR_CODE;
                break;
        }
        return value;
    }
}
