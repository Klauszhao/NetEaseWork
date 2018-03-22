package com.bean;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 页面数据
 * @author zhaonan1
 *
 */
public class GoodsForOnline {
    private int commodityId;
    private String title;
    private String content;
    private String summary;
	private String url;
	private int num;
	private String createtime;
	private BigDecimal price;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
