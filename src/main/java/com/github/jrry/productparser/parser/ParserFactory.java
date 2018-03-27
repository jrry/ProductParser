package com.github.jrry.productparser.parser;

import com.github.jrry.productparser.validators.URLValidator;

public class ParserFactory {
    public Parser getParser(String url) {
        if (URLValidator.isValidURL(url)) {
            if (url.startsWith("https://www.ceneo.pl") || url.startsWith("http://www.ceneo.pl")) {
                return new CeneoParser();
            }
        }
        return null;
    }
}
