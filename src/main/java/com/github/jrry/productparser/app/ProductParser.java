package com.github.jrry.productparser.app;

import com.github.jrry.productparser.converters.ImageLinkToBase64;
import com.github.jrry.productparser.converters.ProductConverter;
import com.github.jrry.productparser.converters.ProductConverterBase64;
import com.github.jrry.productparser.converters.ProductConverterURL;
import com.github.jrry.productparser.dto.Products;
import com.github.jrry.productparser.io.Output;
import com.github.jrry.productparser.io.OutputFactory;
import com.github.jrry.productparser.parser.Parser;
import com.github.jrry.productparser.parser.ParserFactory;
import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class ProductParser {
    private String url;
    private String fileName;
    private ProductConverter productConverter;
    private Parser parser;
    private Document document;
    private Elements elementsProduct;
    private Selector selector;
    private Products products;
    private Output output;

    private final Logger logger = LoggerFactory.getLogger(ProductParser.class);

    public void init(String[] args) {
        if (args.length < 2) {
            logger.error("Incorrect number of parameters");
            return;
        }
        this.productConverter = new ProductConverterBase64();
        if (args.length > 2) {
            if (args[2].equals("-q")) {
                this.productConverter = new ProductConverterURL();
            }
        }
        this.url = args[0];
        this.fileName = args[1];
        this.parser = new ParserFactory().getParser(url);
        this.products = new Products();

        Instant start = Instant.now();

        if (isErrors())
            return;

        generatePages();

        Instant end = Instant.now();
        logger.info("Parser found {} products in {} ms", products.getProductsSize(), Duration.between(start, end).toMillis());

        generateOutput();
    }

    private boolean isErrors() {
        if (isParserNull()) {
            logger.warn("Unsupported URL");
            return true;
        }

        if (isDocumentNull()) {
            logger.error("Error in connection with " + url);
            return true;
        }

        if (isOutputNull()) {
            logger.error("Unsupported file extension");
            return true;
        }

        for (Selector selector : parser.getSelectors()) {
            this.selector = selector;
            elementsProduct = this.selector.getDocumentElementsByClass(document);
            if (!elementsProduct.isEmpty())
                break;
        }

        if (elementsProduct.isEmpty()) {
            logger.error("Incorrect contents. Parser has problems with recognizing content.");
            return true;
        }
        return false;
    }

    private boolean isDocumentNull() {
        document = getUrlWithJsoup(url);
        return document == null;
    }

    private boolean isParserNull() {
        return parser == null;
    }

    private boolean isOutputNull() {
        output = new OutputFactory().getOutput(fileName);
        return output == null;
    }

    private void generatePages() {
        productConverter.convert(products, elementsProduct, selector);
        Element elementPagination = selector.getSelectPagination(document);
        while (elementPagination != null) {
            document = getUrlWithJsoup(parser.getNextPageUrl(elementPagination));
            elementsProduct = selector.getDocumentElementsByClass(document);
            productConverter.convert(products, elementsProduct, selector);
            elementPagination = selector.getSelectPagination(document);
        }
        if (productConverter instanceof ProductConverterURL) {
            ImageLinkToBase64 imageToBase64 = new ImageLinkToBase64(products.getProducts());
            while (!imageToBase64.checkTerminated());
        }
    }

    private void generateOutput() {
        output.generateOutput(products);
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
