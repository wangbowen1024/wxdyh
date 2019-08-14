package com.wxdyh.domain.material;

import java.io.Serializable;

/**
 * MaterialParam class
 * 获取素材列表调用接口所需要的参数实体类
 *
 * @author BowenWang
 * @date 2019/08/05
 */
public class MaterialParam implements Serializable {
    /**
     * 素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     */
    private String type;

    /**
     * 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     */
    private int offset;

    /**
     * 返回素材的数量，取值在1到20之间
     */
    private int count;

    public MaterialParam(String type, int offset, int count) {
        this.type = type;
        this.offset = offset;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MaterialParam{" +
                "type='" + type + '\'' +
                ", offset=" + offset +
                ", count=" + count +
                '}';
    }
}
