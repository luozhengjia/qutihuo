package com.ejunhai.qutihuo.order.dto;

import com.ejunhai.qutihuo.order.model.OrderRepl;

public class OrderReplDto extends OrderRepl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 分页开始位置
     */
    private int offset;

    /**
     * 分页大小
     */
    private int pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
