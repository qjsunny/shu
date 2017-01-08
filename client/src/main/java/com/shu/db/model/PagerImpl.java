package com.shu.db.model;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by admin on 2017/1/8.
 */
public class PagerImpl implements Pager {
    /**
     * @preserve
     */
    private Integer pageSize = 20;

    /**
     * @preserve
     */
    private Integer pageCount;

    /**
     * @preserve
     */
    private Integer recordCount;

    /**
     * @preserve
     */
    private Integer page = 0;

    /**
     * @preserve
     */
    private HashMap<String, String> filter = new HashMap<String, String>();

    /**
     * @preserve
     */
    private String queryString;

    private Integer start = 0;

    public PagerImpl() {

    }

    @SuppressWarnings("unchecked")
    public PagerImpl(HttpServletRequest request) throws Exception {
        String encoding = request.getCharacterEncoding();
        this.page = NumberUtils.toInt(request.getParameter("page"));
        this.pageSize = NumberUtils.toInt(request.getParameter("pageSize"));

        String queryString = "";
        Map<String, String[]> parm = request.getParameterMap();
        for (String key : parm.keySet()) {
            Object val = parm.get(key);
            if (val != null) {
                String[] v = (String[]) val;
                String value = StringUtils.trimToEmpty(v[0]);
                String value_decode = URLDecoder.decode(value, encoding);
                String value_encode = URLEncoder.encode(value, encoding);
                // String value = StringUtils.trimToEmpty(v[0]);

                filter.put(key, value_decode);
                queryString += key + "=" + value_encode + "&";

            }
        }

        this.queryString = StringUtils.substringBeforeLast(queryString, "&");

    }

    public PagerImpl(Integer page, Integer pageSize) {
        if (page < 1) {
            this.page = 0;
        } else {
            this.page = page - 1;
        }

        this.pageSize = pageSize;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize < 1 ? 0 : pageSize;
    }

    @Override
    public Integer getPageCount() {
        return pageCount;
    }

    @Override
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public Integer getRecordCount() {
        return recordCount;
    }

    @Override
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
        if (pageSize < 1)
            pageCount = 1;
        else
            pageCount = recordCount % pageSize == 0 ? (recordCount / pageSize) : (recordCount / pageSize + 1);
    }

    @Override
    public Integer getPage() {
        return page < 0 ? 0 : page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page < 0 ? 0 : page;
        this.start = page * this.pageSize;
    }

    public HashMap<String, String> getFilter() {
        return this.filter;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    // --------------------------------------------------------------------
    // private methods
}
