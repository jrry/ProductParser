package com.github.jrry.productparser.selectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class CeneoSelectorList implements Selector {

    @Override
    public Elements getDocumentElementsByClass(Document document) {
        return document.getElementsByClass("cat-prod-row-body");
    }

    @Override
    public String getSelectProductName(Element product) {
        return product.selectFirst(".cat-prod-row-foto img").attr("alt");
    }

    @Override
    public String getSelectProductPrice(Element product) {
        return product.selectFirst(".cat-prod-row-price span.price").text().replaceAll(",",".");
    }

    @Override
    public String getSelectProductImage(Element product) {
        return Optional.ofNullable(product.selectFirst(".cat-prod-row-foto img").attr("data-original"))
                .filter(StringUtils::isNotEmpty)
                .orElse(product.selectFirst(".cat-prod-row-foto img").attr("src"));
    }
}
