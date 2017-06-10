package cn.fayostyle.utils;

import java.util.List;

/** 封装分页参数
 * Created by HuangPan on 2017/6/10.
 */
public class PageBean<T> {
    //当前页
    private int currentPage = 1;
    //每页显示的数目
    private int pageCount = 6;
    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;


    //每页的数据
    private List<T> pageData;

    private Condition condition;

    public int getTotalPage() {
        if( totalCount % pageCount == 0) {
            totalPage = totalCount / pageCount;
        } else {
            totalPage = totalCount / pageCount + 1;
        }
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
