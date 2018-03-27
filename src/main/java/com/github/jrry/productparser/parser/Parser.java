package com.github.jrry.productparser.parser;

import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.nodes.Element;

import java.util.List;

public interface Parser {
    String getNextPageUrl(Element element);
    List<Selector> getSelectors();
}
