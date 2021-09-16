package com.dubhe.tests;

/**
 * Hello world!
 */
public class Dubhe
{

    private final String message = "Hello World!";

    public Dubhe() {}

    public static void main(String[] args) {
        System.out.println(new Dubhe().getMessage());
    }

    private final String getMessage() {
        return message;
    }

}