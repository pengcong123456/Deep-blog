package com.pcc.deepblog.MyException;

/**
 * @author : 彭聪
 * @date : 2020-10-12 14:17
 **/
public class RunException extends RuntimeException {
    public RunException() {
    }

    public RunException(String message) {
        super(message);
    }
}
