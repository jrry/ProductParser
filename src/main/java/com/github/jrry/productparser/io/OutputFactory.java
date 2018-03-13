package com.github.jrry.productparser.io;

public class OutputFactory {
    public Output getOutput(String filename) {
        if (filename.endsWith(".xml")) {
            return new XmlOutput(filename);
        }
        return null;
    }
}
