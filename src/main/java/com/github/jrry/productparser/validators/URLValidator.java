package com.github.jrry.productparser.validators;

import java.net.MalformedURLException;
import java.net.URL;

public class URLValidator {
    public static boolean isValidURL(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
