package com.apple.tinyurl.exception;

public class UrlNotFoundException extends  RuntimeException{
    public UrlNotFoundException(String message){
        super(message);
    }
}
