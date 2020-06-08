package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main (String[] args) {
        String[] langs = {"Java", "C#", "Python", "php"};

        /*for (int i = 0; i < langs.length; i++) {
            System.out.println("Я хочу выучить " + langs[i]);

        for (String l : langs) {
            System.out.println("Я хочу выучить " + l);
        }         }*/

        List<String> languages = Arrays.asList("Java", "C#", "Python", "php");
        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
