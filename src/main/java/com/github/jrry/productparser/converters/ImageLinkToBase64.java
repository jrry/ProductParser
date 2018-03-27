package com.github.jrry.productparser.converters;

import com.github.jrry.productparser.dto.Image;
import com.github.jrry.productparser.dto.Item;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLinkToBase64 {
    private int numberOfThreads;
    private ExecutorService es;
    private List<Item> products;

    public ImageLinkToBase64(List<Item> products) {
        this.products = products;
        numberOfThreads = Runtime.getRuntime().availableProcessors();
        es = Executors.newFixedThreadPool(numberOfThreads);
        for (int i=0; i<numberOfThreads; i++) {
            es.execute(new Worker(i));
        }
        es.shutdown();
    }

    public boolean checkTerminated() {
        return es.isTerminated();
    }

    private class Worker implements Runnable {
        private int number;

        Worker(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = number; i < products.size() ; i+=numberOfThreads) {
                Item item = products.get(i);
                item.setImageInBase64(new Image(item.getImageInBase64()).getImageInBase64());
                products.set(i, item);
            }
        }
    }
}

