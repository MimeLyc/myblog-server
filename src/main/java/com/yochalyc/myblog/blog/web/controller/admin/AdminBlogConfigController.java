package com.yochalyc.myblog.blog.web.controller.admin;

import com.yochalyc.myblog.blog.core.service.BlogConfigService;
import com.yochalyc.myblog.blog.dal.dao.BlogConfigDAO;
import com.yochalyc.myblog.blog.dal.model.BlogConfigDO;
import com.yochalyc.myblog.blog.exception.BaseException;
import com.yochalyc.myblog.blog.exception.BlogConfigNotFoundException;
import com.yochalyc.myblog.blog.web.model.AdminBlogConfigDTO;
import com.yochalyc.myblog.blog.web.model.BlogConfigDTO;
import com.yochalyc.myblog.blog.web.model.Result;
import com.yochalyc.myblog.blog.web.model.SetBlogConfigRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/admin/config")
public class AdminBlogConfigController {

    @Autowired
    private BlogConfigDAO blogConfigDAO;

    @Autowired
    private BlogConfigService blogConfigService;


    @GetMapping("")
    @ResponseBody
    public Result<AdminBlogConfigDTO> getConfig() {
        try {
            Optional<BlogConfigDO> maybeConfig = blogConfigDAO.findAny();

            return maybeConfig.map(
                    configDO -> new Result<>(
                            AdminBlogConfigDTO
                                    .builder()
                                    .blogName(configDO.getBlogName())
                                    .avatar(configDO.getAvatar())
                                    .github(configDO.getGithub())
                                    .sign(configDO.getSign())
                                    .alipayQrcode(configDO.getAlipayQrcode())
                                    .wxpayQrcode(configDO.getWxpayQrcode())
                                    .hadOldPassword(Objects.nonNull(configDO.getSalt()))
                                    .build()
                    )
            )
                    .orElseThrow(BlogConfigNotFoundException::new);

        } catch (BaseException e) {
            return new Result<>(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }
    }

    @PostMapping("")
    @ResponseBody
    public Result<BlogConfigDTO> setConfig(SetBlogConfigRequest request) {
        try {
            BlogConfigDO configDO = blogConfigService.setConfig(request);


            return new Result<>(new BlogConfigDTO(configDO.getBlogName(), configDO.getAvatar(),
                    configDO.getSign(), configDO.getGithub()));
        } catch (BaseException e) {
            return new Result<>(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            return new Result<>("", e.getMessage());
        }

    }

}
