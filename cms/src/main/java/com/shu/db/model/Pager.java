package com.shu.db.model;

/**
 * Created by admin on 2017/1/8.
 */
public interface Pager {
    public Integer getPageSize();

    public void setPageSize(Integer pageSize);

    public Integer getPageCount();

    public void setPageCount(Integer pageCount);

    public Integer getRecordCount();

    public void setRecordCount(Integer recordCount);

    public Integer getPage();

    public void setPage(Integer page);

    public Integer getStart();
}
