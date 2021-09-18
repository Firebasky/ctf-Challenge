package com.firebasky.fastjsonbypass.Exception;

public class MyException extends RuntimeException{
    private static String header="jdk: "+System.getProperty("java.home")+"\n";
    private String msg;

    public MyException(String msg){
        super(header+msg);
    }
}