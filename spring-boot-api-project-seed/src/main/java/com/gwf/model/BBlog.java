package com.gwf.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "b_blog")
public class BBlog {
    /**
     * 博客主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面url
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 博客发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 上次博客更改时间(默认与release_time相同)
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 内容
     */
    private String content;

    /**
     * 获取博客主键
     *
     * @return id - 博客主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置博客主键
     *
     * @param id 博客主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取关键字
     *
     * @return keyword - 关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键字
     *
     * @param keyword 关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取封面url
     *
     * @return cover_url - 封面url
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面url
     *
     * @param coverUrl 封面url
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    /**
     * 获取博客发布时间
     *
     * @return release_time - 博客发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置博客发布时间
     *
     * @param releaseTime 博客发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取上次博客更改时间(默认与release_time相同)
     *
     * @return update_time - 上次博客更改时间(默认与release_time相同)
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置上次博客更改时间(默认与release_time相同)
     *
     * @param updateTime 上次博客更改时间(默认与release_time相同)
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}