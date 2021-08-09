package com.zj.egov.bean;

import com.zj.egov.util.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象，封装分页查询的相关信息
 *
 * @param <T> 泛型
 */
public class Page<T>
{
    // 当前页码数
    private Integer pageno = 0;
    // 每页显示的记录条数
    private Integer pagesize = Const.PAGE_SIZE;
    // 总页数
    private Integer totalpage = 0;
    // 总条数
    private Integer totalsize = 0;
    // 存储数据的List
    private List<T> dataList;
    // 分页查询的开始下标
    private Integer beginIndex;

    public Page()
    {
    }

    public Page(String pageno)
    {
        this.pageno = pageno == null ? 1 : Integer.parseInt(pageno);
        this.pagesize = Const.PAGE_SIZE;
        this.dataList = new ArrayList<T>();
        this.beginIndex = this.pageno == 0 ? 0 : (this.getPageno() - 1) * this.getPagesize();
    }

    public Integer getPageno()
    {
        return pageno;
    }

    public void setPageno(Integer pageno)
    {
        this.pageno = pageno;
    }

    public Integer getPagesize()
    {
        return pagesize;
    }

    public Integer getTotalpage()
    {
        return totalsize % pagesize == 0 ? totalsize / pagesize : totalsize / pagesize + 1;
    }

    public Integer getTotalsize()
    {
        return totalsize;
    }

    public void setTotalsize(Integer totalsize)
    {
        this.totalsize = totalsize;
    }

    public List<T> getDataList()
    {
        return dataList;
    }

    public String getSql(String sql, String order)
    {
        return sql + " order by " + order + " desc limit " + this.beginIndex + ", " + this.pagesize;
    }

    @Override
    public String toString()
    {
        return "Page{" +
                "pageno=" + pageno +
                ", pagesize=" + pagesize +
                ", totalpage=" + totalpage +
                ", totalsize=" + totalsize +
                ", dataList=" + dataList +
                ", beginIndex=" + beginIndex +
                '}';
    }
}
