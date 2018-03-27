package com.github.jrry.productparser.converters;

import com.github.jrry.productparser.dto.Image;
import com.github.jrry.productparser.dto.Item;
import com.github.jrry.productparser.dto.Products;
import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

public class ProductConverterBase64 implements ProductConverter {

    @Override
    public void convert(Products products, Elements elementsProduct, Selector selector) {
        elementsProduct.forEach(product -> {
            Item item = new Item.Builder()
                    .name(selector.getSelectProductName(product))
                    .minimalPrice(new BigDecimal(selector.getSelectProductPrice(product)))
                    .imageInBase64(new Image(selector.getSelectProductImage(product)).getImageInBase64())
                    .build();
            products.addProduct(item);
        });
    }
}
