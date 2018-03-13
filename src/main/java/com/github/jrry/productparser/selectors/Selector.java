package com.github.jrry.productparser.selectors;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface Selector {
    Elements getDocumentElementsByClass(Document document);
    String getSelectProductName(Element product);
    String getSelectProductPrice(Element product);
    String getSelectProductImage(Element product);

    default Element getSelectPagination(Document document) {
        return document.selectFirst(".pagination-top a");
    }
}
