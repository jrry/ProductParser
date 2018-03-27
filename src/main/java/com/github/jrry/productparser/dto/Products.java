package com.github.jrry.productparser.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class Products {

    @XmlElement(name = "product")
    private List<Item> itemList;

    public Products() {
        itemList = Collections.synchronizedList(new ArrayList<>());
    }

    public void addProduct(Item item) {
        itemList.add(item);
    }

    public List<Item> getProducts() {
        return itemList;
    }

    public int getProductsSize() {
        return itemList.size();
    }

}
