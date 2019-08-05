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

    @XmlElement(name = "Item")
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
