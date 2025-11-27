package com.example.pubbyserver.entity;

import lombok.Data;

import java.util.Date;

/**
 * 吵架记录实体类
 */
@Data
public class QuarrelRecord extends BaseEntity {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 吵架主题
     */
    private String theme;

    /**
     * 吵架原因
     */
    private String reason;

    /**
     * 吵架日期
     */
    private Date quarrelDate;

    /**
     * 反思内容
     */
    private String reflection;
}