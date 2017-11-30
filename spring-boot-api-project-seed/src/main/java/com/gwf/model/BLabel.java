package com.gwf.model;

import javax.persistence.*;

@Table(name = "b_label")
public class BLabel {
    /**
     * 标签主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 获取标签主键
     *
     * @return id - 标签主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置标签主键
     *
     * @param id 标签主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标签名称
     *
     * @return name - 标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名称
     *
     * @param name 标签名称
     */
    public void setName(String name) {
        this.name = name;
    }
}