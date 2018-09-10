package com.highpeak.chat.beans;

import java.util.List;

public class SearchResponseBean {

    private long totalRecords;

    private List<?> entityList;

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<?> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<?> entityList) {
        this.entityList = entityList;
    }
}
