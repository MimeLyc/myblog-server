package com.yochalyc.myblog.blog.core.service.impl;

import com.yochalyc.myblog.blog.core.service.BlogConfigService;
import com.yochalyc.myblog.blog.dal.dao.BlogConfigDAO;
import com.yochalyc.myblog.blog.dal.model.BlogConfigDO;
import com.yochalyc.myblog.blog.exception.BlogConfigNotFoundException;
import com.yochalyc.myblog.blog.exception.WrongBlogPwdException;
import com.yochalyc.myblog.blog.util.Md5Util;
import com.yochalyc.myblog.blog.web.model.SetBlogConfigRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogConfigServiceImpl implements BlogConfigService {

    @Autowired
    private BlogConfigDAO blogConfigDAO;

    @Override
    public BlogConfigDO setConfig(SetBlogConfigRequest request) {
//        如果该请求是设置新密码，但新密码为空的话
        if (request.getIsSetPwdRequest() &&
                StringUtils.isEmpty(request.getViewPassword())) {
            throw new BlogConfigNotFoundException();
        }

        Optional<BlogConfigDO> maybeConfig = blogConfigDAO.findAny();

        return maybeConfig
                .map(configDO -> updateConfig(configDO, request))
                .orElse(saveConfig(request));
    }

    private BlogConfigDO updateConfig(BlogConfigDO configDO, SetBlogConfigRequest request) {

        if (shouldSetNewPwd(configDO, request)) {
            configDO.setPwdAndSalt(request.getViewPassword());
        }

        configDO.setBlogName(request.getBlogName());
        configDO.setGithub(request.getGithub());
        configDO.setAvatar(request.getAvatar());
        configDO.setSign(request.getSign());
        configDO.setAlipayQrcode(request.getAlipayQrcode());
        configDO.setWxpayQrcode(request.getWxpayQrcode());

        blogConfigDAO.saveAndFlush(configDO);

        return configDO;
    }

    private BlogConfigDO saveConfig(SetBlogConfigRequest request) {
        BlogConfigDO configDO = new BlogConfigDO();

        if (request.getIsSetPwdRequest()) {
            configDO.setPwdAndSalt(request.getViewPassword());
        }

        configDO.setBlogName(request.getBlogName());
        configDO.setGithub(request.getGithub());
        configDO.setAvatar(request.getAvatar());
        configDO.setSign(request.getSign());
        configDO.setAlipayQrcode(request.getAlipayQrcode());
        configDO.setWxpayQrcode(request.getWxpayQrcode());

        configDO = blogConfigDAO.saveAndFlush(configDO);

        return configDO;
    }

    private boolean shouldSetNewPwd(BlogConfigDO configDO, SetBlogConfigRequest request) {

        if (StringUtils.isEmpty(configDO.getSalt())) {
            return request.getIsSetPwdRequest();
        }

        if (!Md5Util.isPasswordValid(configDO.getViewPassword(), configDO.getSalt(), request.getOldPassword())) {
            throw new WrongBlogPwdException();
        }

        return true;
    }

}