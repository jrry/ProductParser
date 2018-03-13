package com.github.jrry.productparser.io;

import com.github.jrry.productparser.converters.ProductConverter;
import com.github.jrry.productparser.dto.Products;
import com.github.jrry.productparser.perser.ChooseParser;
import com.github.jrry.productparser.perser.Parser;
import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Input {
    private String url;
    private ProductConverter productConverter;
    private Parser parser;
    private Document document;
    private Element elementPagination;
    private Elements elementsProduct;
    private Selector selector;
    private Products products;

    private final Logger logger = LoggerFactory.getLogger(Input.class);

    public Input(String[] args) {
        this.url = args.length > 0 ? args[0] : "";
        this.parser = new ChooseParser().choose(url);
        this.products = new Products();
        this.productConverter = new ProductConverter();
    }

    public void parse() {
        if (parser == null) {
            logger.warn("Unsupported URL");
            return;
        }

        document = getUrlWithJsoup(url);

        if (document == null) {
            logger.error("Error in connection with " + url);
            return;
        }

        for (Selector selector : parser.getSelectors()) {
            this.selector = selector;
            elementsProduct = this.selector.getDocumentElementsByClass(document);
            if (!elementsProduct.isEmpty())
                break;
        }

        if (elementsProduct.isEmpty()) {
            logger.error("Incorrect contents. Parser has problems with recognizing content.");
            return;
        }

        productConverter.convert(products, elementsProduct, selector);

        nextPages();

        logger.info("Parser found {} products", products.getProductsSize());

        new OutputFactory().getOutput("output.xml").generateOutput(products);
    }

    private void nextPages() {
        elementPagination = selector.getSelectPagination(document);
        while (elementPagination != null) {
            document = getUrlWithJsoup(parser.getNextPageUrl(elementPagination));
            elementsProduct = selector.getDocumentElementsByClass(document);
            productConverter.convert(products, elementsProduct, selector);
            elementPagination = selector.getSelectPagination(document);
        }
    }

    private Document getUrlWithJsoup(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error(e.getClass().getName() + " - " + e.getMessage());
        }
        return null;
    }
}
