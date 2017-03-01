package com.shu.db.model;

/**
 * Created by admin on 2017/1/8.
 */
public enum OrderSort {
    ASC("asc"),

    DESC("desc");

    private String orderType;

    private OrderSort(String orderType) {
        this.orderType = orderType;
    }

    public String getType() {
        return orderType;
    }
}
