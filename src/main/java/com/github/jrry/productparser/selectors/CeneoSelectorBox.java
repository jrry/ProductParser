package com.github.jrry.productparser.selectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class CeneoSelectorBox implements Selector {

    @Override
    public Elements getDocumentElementsByClass(Document document) {
        return document.getElementsByClass("category-item-box");
    }

    @Override
    public String getSelectProductName(Element product) {
        return product.selectFirst(".category-item-box-picture img").attr("alt");
    }

    @Override
    public String getSelectProductPrice(Element product) {
        return product.selectFirst(".category-item-box-price span.price").text().replaceAll(",",".");
    }

    @Override
    public String getSelectProductImage(Element product) {
        return Optional.ofNullable(product.selectFirst(".category-item-box-picture img").attr("data-original"))
                .filter(StringUtils::isNotEmpty)
                .orElse(product.selectFirst(".category-item-box-picture img").attr("src"));
    }
}
