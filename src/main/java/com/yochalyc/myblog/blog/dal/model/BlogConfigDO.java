package com.yochalyc.myblog.blog.dal.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "blog_config", indexes = @Index(name = "id_configId", columnList = "uid"))
public class BlogConfigDO extends BaseDO {

    /**
     * 博客名称
     */
    private String blogName;

    /**
     * 头像
     */
    @Lob
    @Column(columnDefinition = "text")
    private String avatar;

    /**
     * 签名
     */
    @Lob
    @Column(columnDefinition = "text")
    private String sign;

    /**
     * 微信支付二维码
     */
    @Lob
    @Column(columnDefinition = "text")
    private String wxpayQrcode;

    /**
     * 支付宝支付二维码
     */
    @Lob
    @Column(columnDefinition = "text")
    private String alipayQrcode;

    /**
     * github地址
     */
    @Lob
    @Column(columnDefinition = "text")
    private String github;

    /**
     * 加密文章阅读密码
     */
    private String viewPassword;

    /**
     * 加密文章阅读盐
     */
    private String salt;

}
