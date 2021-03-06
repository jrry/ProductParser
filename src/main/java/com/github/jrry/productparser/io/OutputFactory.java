package com.github.jrry.productparser.io;

public class OutputFactory {
    public Output getOutput(String filename) {
        if (filename.endsWith(".xml")) {
            return new XmlOutput(filename);
        }
        if (filename.endsWith(".json")) {
            return new JsonOutput(filename);
        }
        return null;
    }
}
