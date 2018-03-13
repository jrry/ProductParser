package com.github.jrry.productparser.perser;

import java.net.MalformedURLException;
import java.net.URL;

public class ChooseParser {
    public Parser choose(String url) {
        if (isValidURL(url)) {
            if (url.startsWith("https://www.ceneo.pl") || url.startsWith("http://www.ceneo.pl")) {
                return new CeneoParser();
            }
        }
        return null;
    }

    private boolean isValidURL(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
