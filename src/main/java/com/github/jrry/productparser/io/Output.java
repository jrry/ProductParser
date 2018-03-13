package com.github.jrry.productparser.io;

import com.github.jrry.productparser.dto.Products;

public interface Output {
    void generateOutput(Products products);
}
