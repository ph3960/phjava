package com.faw.usertestall.domain.common;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class PageQuery<T> implements Serializable {

    private static final long serialVersionUID = 8474064800776503397L;


    @Min(value = 1, message = "页号必须为正整数")
    private Integer pageNo = 1;

    @Max(value = 100, message = "每页不得超过100条")
    private Integer pageSize = 10;

    private T query;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}
