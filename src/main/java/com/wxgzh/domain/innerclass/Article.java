package com.wxgzh.domain.innerclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Articles class
 *
 * @author BowenWang
 * @date 2019/08/04
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Article implements Serializable {

    @XmlElement(name = "Items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
