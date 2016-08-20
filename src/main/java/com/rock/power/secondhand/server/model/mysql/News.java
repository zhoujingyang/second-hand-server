package com.rock.power.secondhand.server.model.mysql;

import java.util.Date;

/**
 * Created by yanshi on 16/8/20.
 */
public class News {
    private Integer id;
    private Long pageId;
    private String title;
    private Date createTime;
    private String source;
    private String author;
    private String html;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", pageId=" + pageId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", html='" + html + '\'' +
                '}';
    }
}
