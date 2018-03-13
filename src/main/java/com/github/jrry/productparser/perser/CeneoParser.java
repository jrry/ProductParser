package com.github.jrry.productparser.perser;

import com.github.jrry.productparser.selectors.CeneoSelectorBox;
import com.github.jrry.productparser.selectors.CeneoSelectorList;
import com.github.jrry.productparser.selectors.Selector;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class CeneoParser implements Parser {
    @Override
    public String getNextPageUrl(Element element) {
        return "https://www.ceneo.pl" + element.attr("href");
    }

    @Override
    public List<Selector> getSelectors() {
        List<Selector> selectors = new ArrayList<>();
        selectors.add(new CeneoSelectorList());
        selectors.add(new CeneoSelectorBox());
        return selectors;
    }
}
