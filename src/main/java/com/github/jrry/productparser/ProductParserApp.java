package com.github.jrry.productparser;

import com.github.jrry.productparser.io.Input;

public class ProductParserApp {

    public static void main(String[] args) {
        Input input = new Input(args);
        input.parse();
    }

}
