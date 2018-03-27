package com.github.jrry.productparser.converters;

import com.github.jrry.productparser.dto.Products;
import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.select.Elements;

public interface ProductConverter {
    void convert(Products products, Elements elementsProduct, Selector selector);
}
