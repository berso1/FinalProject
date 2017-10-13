package com.example;

import java.util.Random;

//Class that return a random joke
public class Jokes {

    private final String[] jokes;
    private final Random random;

    public Jokes() {
        jokes = new String[5];
        jokes[0] = "The closest a person ever comes to perfection is when he fills out a job application form.";
        jokes[1] = "Q: how many programmers does it take to change a light bulb?\n\n" +
                   "A: none, that's a hardware problem.";
        jokes[2] = "If you put a million monkeys at a million keyboards, one of them will eventually write a Java program.\n\n"+
                   "The rest of them will write Perl programs.";
        jokes[3] = "Q: Whats the object-oriented way to become wealthy?\n\n" +
                   "A: Inheritance";
        jokes[4] = "To avoid taking down my Christmas lights, I’m turning my house into an Italian restaurant.";

        random = new Random();
    }

    public String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
