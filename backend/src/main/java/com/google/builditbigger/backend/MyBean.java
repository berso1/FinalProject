package com.google.builditbigger.backend;


import com.example.Jokes;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public String getData() {
        Jokes jokes = new Jokes();
        String joke = jokes.getJoke();
        return joke;
    }

    public void setData(String data) {
        myData = data;
    }
}