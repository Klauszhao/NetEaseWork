package com.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;

    private String title;

    private String summary;

    private BigDecimal price;

    private String content;

    private String url;

    private Date createtime;

    private Date updatetime;

    private Short isDeleted;

    private Short isEditor;

    private Short parentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Short getIsEditor() {
        return isEditor;
    }

    public void setIsEditor(Short isEditor) {
        this.isEditor = isEditor;
    }

    public Short getParentid() {
        return parentid;
    }

    public void setParentid(Short parentid) {
        this.parentid = parentid;
    }
}