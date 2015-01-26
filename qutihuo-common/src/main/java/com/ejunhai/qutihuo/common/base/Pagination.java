package com.ejunhai.qutihuo.common.base;

import java.io.Serializable;

public class Pagination implements Serializable {

    private static final long serialVersionUID = -3139317401666177861L;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总页码
     */
    private Integer totalPage;

    /**
     * 开始位置
     */
    private Integer offset;

    /**
     * 页范围
     */
    private int[] pageRange;

    public Pagination(Integer pageNo, Integer totalCount) {
        this(pageNo, totalCount, 10, 9);
    }

    public Pagination(Integer pageNo, Integer totalCount, Integer pageSize, Integer aroundSize) {
        // 参数兼容处理
        this.pageNo = pageNo == null || pageNo.equals(0) ? 1 : pageNo;
        this.pageSize = pageSize == null ? 20 : pageSize;
        this.totalCount = (totalCount == null ? 0 : totalCount);
        aroundSize = aroundSize == null || aroundSize.equals(0) ? 9 : aroundSize;

        // 计算扩展属性
        this.totalPage = (this.totalCount + this.pageSize - 1) / this.pageSize;
        this.totalPage = this.totalPage == 0 ? 1 : this.totalPage;
        this.offset = (this.pageNo - 1) * this.pageSize;
        this.pageRange = this.calPageRange(this.pageNo, totalPage, aroundSize);
    }

    /**
     * 计算页面页码显示范围
     * 
     * @param curPage
     * @param totalPage
     * @param aroundSize
     * @return
     */
    private int[] calPageRange(Integer curPage, Integer totalPage, Integer aroundSize) {
        // 兼容处理
        totalPage = totalPage == null || totalPage.equals(0) ? 1 : totalPage;

        // 计算开始结束位置
        int startIndex = curPage - aroundSize / 2;
        startIndex = startIndex < 1 ? 1 : startIndex;

        Integer endIndex = startIndex + aroundSize - 1;
        endIndex = endIndex < totalPage ? endIndex : totalPage;
        if (endIndex - startIndex <= aroundSize) {
            startIndex = endIndex - aroundSize + 1;
            startIndex = startIndex < 1 ? 1 : startIndex;
        }

        int[] pagination = { startIndex, endIndex };
        return pagination;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public int[] getPageRange() {
        return pageRange;
    }

    public void setPageRange(int[] pageRange) {
        this.pageRange = pageRange;
    }

}
