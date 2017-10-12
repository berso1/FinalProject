package com.example;

import java.util.Random;

public class Jokes {

    private String[] jokes;
    private Random random;

    public Jokes() {
        jokes = new String[4];
        jokes[0] = "Q: Why programmers like UNIX:\n\n" +
                   "A: unzip, strip, touch, finger, grep, mount, fsck, more, yes, fsck, fsck, fsck, umount, sleep";
        jokes[1] = "Q: how many programmers does it take to change a light bulb?\n\n" +
                   "A: none, that's a hardware problem.";
        jokes[2] = "If you put a million monkeys at a million keyboards, one of them will eventually write a Java program.\n\n"+
                   "The rest of them will write Perl programs.";
        jokes[3] = "Q: Whats the object-oriented way to become wealthy?\n\n" +
                   "A: Inheritance";


        random = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
