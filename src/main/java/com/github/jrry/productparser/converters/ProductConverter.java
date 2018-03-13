package com.github.jrry.productparser.converters;

import com.github.jrry.productparser.dto.Item;
import com.github.jrry.productparser.dto.Products;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.github.jrry.productparser.selectors.Selector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

public class ProductConverter {

    private final Logger logger = LoggerFactory.getLogger(ProductConverter.class);

    public void convert(Products products, Elements elementsProduct, Selector selector) {
        for (Element product : elementsProduct) {
            Item item = new Item();
            item.setName(selector.getSelectProductName(product));
            item.setMinimalPrice(new BigDecimal(selector.getSelectProductPrice(product)));

            try {
                ImageToBase64 imageToBase64 = new ImageToBase64(selector.getSelectProductImage(product));
                try {
                    item.setImageBase64(imageToBase64.getImageInBase64());
                } catch (IOException e) {
                    logger.warn(e.getClass().getName() + " - " + e.getMessage());
                }
            } catch (MalformedURLException e) {
                logger.error(e.getClass().getName() + " - " + e.getMessage());
            }

            products.addProduct(item);
        }
    }
}
